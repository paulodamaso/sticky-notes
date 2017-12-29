package main.task;

/**
 * <p> Top-level interface modeling group of {@link Task} objects.
 * 
 * @author paulodamaso
 *
 */
public interface Tasks  {
	
	public Iterable<Task> iterate();
	
	public Task add(String description);

}