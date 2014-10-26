package toDo;


import java.io.FileReader;
import com.google.gson.Gson;

public class ListTask {
	
	public final static String DEFAULT_FILE_NAME = "taskList.json";

	// Iterates though all task in the TaskBook and prints info about them.
	static void Print(TaskBook taskBook) {
		for (Task task : taskBook.getTaskList()) {
			System.out.println("Task ID: " + task.getTask());
			System.out.println("  Context: " + task.getContext());
			System.out.println("  Project: " + task.getProject());
			System.out.println("  Priority: " + task.getPriority());
			
		}
	}

	// Main function: Reads the entire taskbook from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		// Read the existing address book.
		TaskBook taskBook = gson.fromJson(new FileReader(filename), TaskBook.class);

		Print(taskBook);
	}

}
