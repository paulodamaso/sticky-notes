package main;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p> {@link Tasks} interface read from memory. 
 * 
 * @author paulodamaso
 *
 */
public final class MemoryTasks implements Tasks {
    private final Iterable<Task> tasks;
    
    public MemoryTasks (Iterable<Task> tasks) {
      this.tasks = new ArrayList<Task>((Collection<Task>)tasks);
    }
    
    public Iterable<Task> iterate() {
      return this.tasks;
    }
    

    public Tasks add(Task task) {
    	return new MemoryTasks(new ArrayList<Task>((Collection<Task>)tasks)).add(task);
    }

}
