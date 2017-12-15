package main;
import java.util.Date;

/**
 * <p> A task.
 * 
 * @author paulodamaso
 *
 */
public interface Task  {
	
	public int id();
	
	public String description();
	
	public Date date();
}
