package main;

/**
 * <p> A simple implementation for {@link Task}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleTask implements Task{
	
	private final int id;
    private final String description;
    
    public SimpleTask(int id, String description){
    	this.id = id;
    	this.description = description;
    }
    
    public int id() {
    	return this.id;    	
    }
    
    public String description() {
      return this.description;
    }   

}
