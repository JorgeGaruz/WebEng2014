package rest.ToDo;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

	/**
     * Default constructor
     */
    public ApplicationConfig() {
    	this(new TaskBook());
    }


    /**
     * Main constructor
     * @param taskBook a provided task book
     */
    public ApplicationConfig(final TaskBook taskBook) {
    	register(TaskBookService.class);
    	register(MOXyJsonProvider.class);
    	register(new AbstractBinder() {

			@Override
			protected void configure() {
				bind(taskBook).to(TaskBook.class);
			}});
	}	

}
