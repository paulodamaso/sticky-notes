package main;
import java.util.Date;

/**
 * <p> A task.
 * 
 * @author paulodamaso
 *
 */
/* 
 * @todo #2 i am not so sure about extending the printable interface here, but it would be
 *  worse to typecast it in my tasks implementations.  
 */
public interface Task extends Printable {
	
	public int id();
	
	public String description();
	
	public Date date();
}
