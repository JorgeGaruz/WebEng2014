package toDo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static toDo.Priority.*;

public class AddTask {

public final static String DEFAULT_FILE_NAME = "taskList.json";
public static int numTask;

	// This function fills in a Person message based on user input.
	public static Task PromptForTask(BufferedReader stdin, PrintStream stdout)
			throws IOException {
		 Task task = new Task();
	
	task.setTask(numTask);
	//numTask++;
	
	stdout.print("Enter context: ");
	task.setContext(stdin.readLine());
	
	stdout.print("Enter project: ");
	task.setProject(stdin.readLine());

	stdout.print("Enter priority(HIGH,MID,LOW): ");
	String priority = stdin.readLine();//Por defecto la prioridad es LOW
	if (priority.equals("HIGH")){
		task.setPriority(HIGH);
	}
	else if (priority.equals(MID)){
		task.setPriority(MID);
	}
	else{
		task.setPriority(LOW);
	}
		return task;
	}
	
	// Main function: Reads the entire task list from a file,
	// adds one task based on user input, then writes it back out to the same
	// file.
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename=args[0];
		}
	
		TaskBook taskBook = new TaskBook();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
		// Read the existing address book.
	try {
		taskBook = gson.fromJson(new FileReader(filename), TaskBook.class);
	} catch (FileNotFoundException e) {
		System.out.println(filename
				+ ": File not found.  Creating a new file.");
	}
	//Conseguir el último número de la tarea para añadir apartir de dicho número.
	numTask = taskBook.getTaskList().size()+1;
	
	
	// Add an task
	//try{
		Task task= PromptForTask(new BufferedReader(
				new InputStreamReader(System.in)), System.out);
	taskBook.addTask(task);
	
	// Write the new address book back to disk.
	FileWriter output = new FileWriter(filename);
	output.write(gson.toJson(taskBook));
	output.close();
	/*} catch(Exception e){
		System.out.println(e.getMessage());
		System.out.println(e.toString());
	}*/
	}
}