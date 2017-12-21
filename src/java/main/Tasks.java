package main;

/**
 * <p> A group of {@link Task} objects.
 * 
 * @author paulodamaso
 *
 */
public interface Tasks<T extends Task>  {
	
	public Iterable<T> iterate();
	
	public T add(String description);

}
