package main;

/**
 * <p> A group of {@link Task} objects.
 * 
 * @author paulodamaso
 *
 */
public interface Tasks  {
	
	public Iterable<Task> iterate();
	
	public Tasks add(Task task);

}
