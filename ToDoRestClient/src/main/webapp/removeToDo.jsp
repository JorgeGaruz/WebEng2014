<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="rest.todo.*" %>
<%@ page import="javax.ws.rs.client.*" %>
<%@ page import="javax.ws.rs.core.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo Remove</title>
</head>
<body>

<% Client cliente = ClientBuilder.newClient();
	int tarea = Integer.parseInt((String)request.getAttribute("task"));
	Response respuesta = cliente.target("http://localhost:8085/tasks/task/"+ tarea)
	.request(MediaType.APPLICATION_JSON)
	.delete();%>
	
Task nº <%=Integer.toString(tarea)%> remove
	
</body>
</html>