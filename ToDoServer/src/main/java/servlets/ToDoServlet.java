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

import toDo.TaskBook;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/listTask" })
public class ToDoServlet extends HttpServlet  {

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
			req.setAttribute("taskBook", taskBook);
			RequestDispatcher ReqDis = getServletContext().getRequestDispatcher("/principal.jsp");
			ReqDis.forward(req, resp);
		}
		

	}

}
