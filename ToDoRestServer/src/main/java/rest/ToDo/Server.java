package rest.ToDo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Server {
	private static final Logger LOGGER = Grizzly.logger(Server.class);
	public final static String DEFAULT_FILE_NAME = "taskList.json";
	private static TaskBook taskBook;
	
	public static void main(String[] args) throws IOException {
		taskBook = new TaskBook();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Read the existing address book.
		try {
			taskBook = gson.fromJson(new FileReader(DEFAULT_FILE_NAME), TaskBook.class);
			
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  Creating a new file.");
		}

		
		URI uri = UriBuilder.fromUri("http://localhost/").port(8085).build();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri,
				new ApplicationConfig(taskBook));
		try {
			server.start();
			LOGGER.info("Press 's' to shutdown now the server...");
			while(true){
				int c = System.in.read();
				if (c == 's')
					break;
			}
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
		} finally {
			FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			gson = new GsonBuilder().setPrettyPrinting().create();
			output.write(gson.toJson(taskBook));
			output.close();
			server.stop();
			LOGGER.info("Server stopped");
		}
	}
	
}
