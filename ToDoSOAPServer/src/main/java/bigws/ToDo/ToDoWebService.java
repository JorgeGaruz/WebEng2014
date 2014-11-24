package bigws.ToDo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import toDo.Task;
import toDo.TaskBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebService
public class ToDoWebService {
	
	public final static String DEFAULT_FILE_NAME = "taskList.json";
	
	@WebMethod
	public TaskBook listToDo (){
	
		TaskBook taskBook = new TaskBook();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
		// Read the existing address book.
	try {
		taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);
	} catch (FileNotFoundException e) {
		System.out.println(DEFAULT_FILE_NAME
				+ ": File not found.  Creating a new file.");
	}

		return taskBook;
	}
	
	@WebMethod
	public TaskBook removeToDo(@WebParam int task){
		
			TaskBook taskBook = new TaskBook();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
			// Read the existing address book.
		try {
			taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);
			if (task<taskBook.getTaskList().size()){
				taskBook.getTaskList().remove(taskBook.getTaskList().get(task+1));
			}
			else{
				taskBook.getTaskList().remove(taskBook.getTaskList().get(task));
			}
			
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			
			output.write(gson.toJson(taskBook));
			output.close();
	
			return taskBook;
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  Creating a new file.");
			return taskBook;
		} catch(Exception e){//Excepcion para comprobar si ha podido borrar correctamente la tarea
			return null;
		}
		
	}
	
	@WebMethod
	public TaskBook addToDo (@WebParam Task task){
	
			TaskBook taskBook = new TaskBook();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
			// Read the existing address book.
		try {
			taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);
	
		//Conseguir el último número de la tarea para añadir apartir de dicho número. Coge la task de último valor del Json
		//y lo añade a continuación
			task.setTask(taskBook.getTaskList().size()+1);
			
			taskBook.addTask(task);
			
			// Write the new address book back to disk.
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			output.write(gson.toJson(taskBook));
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  Creating a new file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			return taskBook;
	}

}
