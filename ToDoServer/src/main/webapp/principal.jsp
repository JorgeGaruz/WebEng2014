<!DOCTYPE html> 
<%@ page import="toDo.*" %>
<head>
<title>ToDo Online</title>
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
<br />
<br />
<form method="get" action="search">
		Search by context <input type="text" name="context"/><br /> 
		Search by Priority <input type="text" name="priority"/><br /> 
		Search by Project  <input type="text" name="project"/><br /> 
			<input type="submit" value="Submit"/>
	</form>
	
	</ul>
</body>
</html>
