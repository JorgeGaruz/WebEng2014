package servlets.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/addToDo", "/removeToDo","listToDo" })
public class ToDoServlet extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String opcion = req.getServletPath();
		
		if(opcion.substring(1).equals("addToDo")){
			RequestDispatcher ReqDis = getServletContext().getRequestDispatcher("/addToDo.jsp");
			//La logica del Task va en el jsp para poder desacoplar
			ReqDis.forward(req, resp);
		}
		else if (opcion.substring(1).equals("removeToDo")){
			RequestDispatcher ReqDis = getServletContext().getRequestDispatcher("/removeToDo.jsp");
			req.setAttribute("task", req.getParameter("task"));
			ReqDis.forward(req, resp);
		}
		else{//listToDo
			RequestDispatcher ReqDis = getServletContext().getRequestDispatcher("/listToDo.jsp");
			ReqDis.forward(req, resp);
		}
		

	}

}
