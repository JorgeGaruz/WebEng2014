<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="toDo.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search ToDoServer</title>
</head>
<body>
	<ul><h1>Task	Context		Project		Priority</h1>
	 <% TaskBook taskBook= (TaskBook) request.getAttribute("taskBook");

	for (Task task : taskBook.getTaskList()) {
		String linea ="<li>" + task.getTask() +"	"+ task.getContext().toString() +"	"+ task.getProject().toString()+"	" + task.getPriority().toString()+ "</li>";
%>	
	<%= linea %>
<%
	}
%> 
	
	</ul>
</body>
</html>