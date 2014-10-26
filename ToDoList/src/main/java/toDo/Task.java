package toDo;

import static toDo.Priority.*;

public class Task {

	public int task;
	public String context;
	public String project;
	public Priority priority= LOW;
	
	public int getTask() {
		return task;
	}
	public void setTask(int task) {
		this.task = task;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}
