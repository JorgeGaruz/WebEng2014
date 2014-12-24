package rest.ToDo;

import java.net.URI;

public class Task {
	
/*
 * Los nombres estan en español ya que si se ponen en ingles, interfieren con los get y set y gradle no
 * realiza el servicio 'server'.
 */
	public int tarea;
	public String contexto;
	public String projecto;
	public Priority prioridad= Priority.LOW;
	private URI href;
	
	public int getTask() {
		return tarea;
	}
	public void setTask(int task) {
		this.tarea = task;
	}
	public String getContext() {
		return contexto;
	}
	public void setContext(String context) {
		this.contexto = context;
	}
	public String getProject() {
		return projecto;
	}
	public void setProject(String project) {
		this.projecto = project;
	}
	public Priority getPriority() {
		return prioridad;
	}
	public void setPriority(Priority priority) {
		this.prioridad = priority;
	}
	
	public void setHref(URI href) {
		this.href = href;
	}
	
	public URI getHref() {
		return href;
	}

}
