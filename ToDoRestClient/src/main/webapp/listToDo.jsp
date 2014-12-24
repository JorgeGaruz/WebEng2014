<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="rest.todo.*" %>
<%@ page import="javax.ws.rs.client.*" %>
<%@ page import="javax.ws.rs.core.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo List</title>
</head>
<body>


	<h1>List ToDo</h1>

<br />

<ul><h1>Task	Context		Project		Priority</h1>
	 <% 
	 Client cliente = ClientBuilder.newClient();
	 Response respuesta = cliente.target("http://localhost:8085/tasks")
	.request(MediaType.APPLICATION_JSON)
	.get();
	TaskBook taskBook = respuesta.readEntity(TaskBook.class);

	if (!taskBook.getTaskList().isEmpty()&& taskBook!=null){
		
		for (Task task : taskBook.getTaskList()) {
			String linea ="<li>" + task.getTask() +"	"+ task.getContext().toString() +"	"+ task.getProject().toString()+"	" + task.getPriority().toString()+ "</li>";
%>	
			<%= linea %>
<%
		}
	}
%> 

</body>
</html>
