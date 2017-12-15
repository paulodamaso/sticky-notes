package main;
import java.util.Date;

/**
 * <p> A simple implementation for {@link Task}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleTask implements Task {
	
	private final int id;
    private final Date date;
    private final String description;
    
    SimpleTask(int id, Date date, String description){
    	this.id = id;
    	this.date = date;
    	this.description = description;
    }
    
    public int id() {
    	return this.id;    	
    }
    
    public String description() {
      return this.description;
    }
    
    public Date date() {
      return this.date;
    }

	@Override
	public void print() {
		System.out.println(id +" - " + description);
	}

}
