package servlets;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import toDo.Task;
import toDo.TaskBook;

/**
 * Servlet implementation class SearchServlet
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet {
	public final static String DEFAULT_FILE_NAME = "taskList.json";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		TaskBook taskBook= new TaskBook();
		Gson gson = new Gson();
		
		try{
			taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);
		} catch (Exception e){
			taskBook=null;
		}
		if (taskBook!=null){
			TaskBook filtro = filtrar(req,taskBook);
			req.setAttribute("taskBook", filtro);
			RequestDispatcher ReqDis = getServletContext().getRequestDispatcher("/Search.jsp");
			ReqDis.forward(req, resp);
		}
		

	}
	
	private static TaskBook filtrar (HttpServletRequest req,TaskBook taskBook){
		TaskBook filtro = new TaskBook();
		String context = req.getParameter("context");
		String priority = req.getParameter("priority");
		String project = req.getParameter("project");

		for (Task task : taskBook.getTaskList()) {
			if (task.getContext().equals(context)||(task.getPriority().toString().equals(priority))||
					(task.getProject().equals(project))){
				filtro.addTask(task);
			}
		}
		return filtro;
		
	}

}
