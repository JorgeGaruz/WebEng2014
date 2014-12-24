<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="rest.todo.*" %>
<%@ page import="javax.ws.rs.client.*" %>
<%@ page import="javax.ws.rs.core.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo Add</title>
</head>
<body>


<br />

	 <% 

	 
	 Task task = new Task();
	 //añadir datos del formulario
	 task.setContext((String)request.getParameter("context"));
	 String priority = (String)request.getParameter("priority");
	 if (priority.equals("HIGH")){
			task.setPriority(Priority.HIGH);
		}
		else if (priority.equals("MID")){
			task.setPriority(Priority.MID);
		}
		else{
			task.setPriority(Priority.LOW);
		}
	 task.setProject((String)request.getParameter("project"));

	 Client cliente = ClientBuilder.newClient();
	 Response respuesta = cliente.target("http://localhost:8085/tasks")
	.request(MediaType.APPLICATION_JSON)
	.post(Entity.entity(task, MediaType.APPLICATION_JSON));
%>	

Task with Context <i><%=task.getContext()%></i>, Priority <i><%=task.getPriority() %></i> and Project <i><%= task.getProject() %></i> add

</body>
</html>
