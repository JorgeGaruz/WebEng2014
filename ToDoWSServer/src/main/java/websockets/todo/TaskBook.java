package websockets.todo;

import java.util.ArrayList;
import java.util.List;

public class TaskBook {
	
	private int nextId = 1;
	
	/**
	 * The value of next unique identifier.
	 * @return the next unique identifier.
	 */
	public int getNextId() {
		return nextId;
	}

	public void setNextId(int nextId) {
		this.nextId = nextId;
	}
	
	private List<Task> taskList = new ArrayList<Task>();

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> tasks) {
		this.taskList = tasks;
	}

	public void addTask(Task task) {
		taskList.add(task);
	}
	
	/**
	 * Returns the old next identifier and increases the new value in one.
	 * @return an identifier.
	 */
	public int nextId() {
		int oldValue = nextId;
		nextId++;
		return oldValue;
	}

}
