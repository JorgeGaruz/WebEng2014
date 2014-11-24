package bigws.ToDo;

import javax.xml.ws.Endpoint;

public class Server {
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8085/ToDoList", new ToDoWebService());
	}


}
