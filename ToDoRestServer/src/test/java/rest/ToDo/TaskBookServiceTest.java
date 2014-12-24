package rest.ToDo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.junit.After;
import org.junit.Test;


/**
 * A simple test suite
 *
 */
public class TaskBookServiceTest {

	HttpServer server;

	@Test
	public void serviceIsAlive() throws IOException {
		// Prepare server
		TaskBook tb = new TaskBook();
		launchServer(tb);

		// Request the task book
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8282/tasks")
				.request().get();
		assertEquals(200, response.getStatus());
		assertEquals(0, response.readEntity(TaskBook.class).getTaskList()
				.size());
	}

	private void launchServer(TaskBook ab) throws IOException {
		URI uri = UriBuilder.fromUri("http://localhost/").port(8282).build();
		server = GrizzlyHttpServerFactory.createHttpServer(uri,
				new ApplicationConfig(ab));
		server.start();
	}

	@After
	public void shutdown() {
		if (server != null) {
			server.shutdownNow();
		}
		server = null;
	}

}
