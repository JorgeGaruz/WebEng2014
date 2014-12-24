package rest.ToDo;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;


/**
 * A service that manipulates tasks in an ToDo book.
 *
 */
@Path("/tasks")
public class TaskBookService {

	/**
	 * The (shared) address book object. 
	 */
	@Inject
	TaskBook taskBook;

	/**
	 * A GET /tasks request should return the address book in JSON.
	 * @return a JSON representation of the address book.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TaskBook getTaskBook() {
		return taskBook;
	}

	/**
	 * A POST /tasks request should add a new entry to the task book.
	 * @param info the URI information of the request
	 * @param task the posted entity
	 * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(@Context UriInfo info, Task task) {
		taskBook.addTask(task);
		task.setTask(taskBook.nextId());
		task.setHref(info.getAbsolutePathBuilder().path("tasks/{task}").build(task.getTask()));
		return Response.created(task.getHref()).entity(task).build();
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
	@GET
	@Path("/tasks/{task}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTask(@PathParam("task") int task) {
		for (Task tasks : taskBook.getTaskList()) {
			if (tasks.getTask() == task) {
				return Response.ok(tasks).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * A PUT /tasks/task/{id} should update a entry if exists
	 * @param info the URI information of the request
	 * @param tasks the posted entity
	 * @param task the unique identifier of a task
	 * @return a JSON representation of the new updated entry or 400 if the id is not a key
	 */
	@PUT
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@Context UriInfo info,
			@PathParam("id") int task, Task tasks) {
		for (int i = 0; i < taskBook.getTaskList().size(); i++) {
			if (taskBook.getTaskList().get(i).getTask() == task) {
				tasks.setTask(task);
				tasks.setHref(info.getAbsolutePath());
				taskBook.getTaskList().set(i, tasks);
				return Response.ok(tasks).build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	/**
	 * A DELETE /tasks/task/{id} should delete a entry if exists
	 * @param task the unique identifier of a task
	 * @return 204 if the request is successful, 404 if the id is not a key
	 */
	@DELETE
	@Path("/task/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@PathParam("id") int task) {
		for (int i = 0; i < taskBook.getTaskList().size(); i++) {
			if (taskBook.getTaskList().get(i).getTask() == task) {
				taskBook.getTaskList().remove(i);
				taskBook.setNextId(taskBook.getNextId()-1);
				return Response.noContent().build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
