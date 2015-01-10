package websockets.todo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


@ServerEndpoint(value = "/todos")
public class ToDoServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	public final static String DEFAULT_FILE_NAME = "taskList.json";
	private static TaskBook taskBook;

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected with " + session.getId());
		taskBook=loadTodo();
		try {
			session.getBasicRemote().sendText("Connection Established (session "+ session.getId()+")");
		} catch (IOException ex) {
		ex.printStackTrace();
		}
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String response = "";
		boolean reenviar = true;
		//Necesario que el string contenga :, por eso todos los mensajes serán de la forma addToDo: o getTaskList:
		String option = message.substring(0,message.indexOf(":"));
		logger.info(message);
		switch (option) {
		case "addToDo":
			logger.info("Service required: " + option);
			response = addTask(message,gson);
			logger.info(response);
			break;
		case "removeToDo":
			logger.info("Service required: " + option);
			response = removeTask(message,gson);
			logger.info(response);
			break;
		case "getTaskList":
			logger.info("Service required: " + option);
			response = "getTaskList:";
			response += gson.toJson(taskBook.getTaskList());
			logger.info(response);
			break;
		case "quit":
			logger.info("Service required: " + option);
			try {
				session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
						"Session end"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			break;
		default:
			reenviar=false;
		}
		
		if (reenviar){
			for (Session s : session.getOpenSessions()){
				try{
					if(s.isOpen()){
						s.getBasicRemote().sendObject(message);
					}
				}
				catch(Exception e){
					
				}
			}
		}
		return response;
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}
	
	//https://tyrus.java.net/documentation/1.0/user-guide.html
    @OnError
    public void onError(Session session, Throwable t) {
        //t.printStackTrace();
        logger.log(Level.SEVERE,t.toString());
    }
    
    private String addTask(String message,Gson gson){
    	Task task = gson.fromJson(message.substring(1+message.indexOf(":")), Task.class);
    	//Los parámetros de la tarea están separados por : ;addToDo:contexto:projecto:priority
    	/*message = message.substring(1+message.indexOf(":"),message.length());
    	String contexto = message.substring(0,message.indexOf(":"));
    	message = message.substring(1+message.indexOf(":"),message.length());
    	String proyecto = message.substring(0,message.indexOf(":"));
    	message = message.substring(1+message.indexOf(":"),message.length());
    	String priority = message;
    	Task task = new Task(); task.setContext(contexto);task.setProject(proyecto);
    	if (priority.equals("HIGH")){
    		task.setPriority(Priority.HIGH);
    	}
    	else if (priority.equals("MID")){
    		task.setPriority(Priority.MID);
    	}
    	else{
    		task.setPriority(Priority.LOW);
    	}
*/	logger.info(task.toString());
    	task.setTask(taskBook.nextId());
    	taskBook.addTask(task);
    	FileWriter output;
		try {
			output = new FileWriter(DEFAULT_FILE_NAME);

		output.write(gson.toJson(taskBook));
		output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "addTask:Task at add task";
		}
    	return "addTask:Task add";
    }
    
    private String removeTask(String message,Gson gson){
    	//Lo que hay a continuación del : es el id de la tarea
    	int id = Integer.parseInt(message.substring(1+message.indexOf(":"),message.length()));
    	logger.info("Task to remove: "+id);

		for (int i = 0; i < taskBook.getTaskList().size(); i++) {
			if (taskBook.getTaskList().get(i).getTask() == id) {
				logger.info("Task remove");
				taskBook.getTaskList().remove(i);
				taskBook.setNextId(taskBook.getNextId()-1);
			}
		}
    	FileWriter output;
		try {
			output = new FileWriter(DEFAULT_FILE_NAME);

		output.write(gson.toJson(taskBook));
		output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "removeTask:Fail at remove task";
		}
    	return "removeTask:Task remove";
    }
    
    private TaskBook loadTodo (){
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	try {
			taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);

		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return taskBook;
    }
}
