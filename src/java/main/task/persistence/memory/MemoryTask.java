package main.task.persistence.memory;

import main.task.Task;

/**
 * <p> A simple implementation for {@link Task} representing a {@link Task} with data held in memory.
 * 
 * @author paulodamaso
 *
 */
public final class MemoryTask implements Task{
	
	private final int id;
    private final String description;
    
    public MemoryTask(int id, String description){
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
