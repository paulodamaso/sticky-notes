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
    private final ArrayList<Task> tasks;
    //private final Tasks tasks;
    
    
    public MemoryTasks (Iterable<Task> tasks) {
      this.tasks = new ArrayList<Task>((Collection<Task>)tasks);
    }
    
    public Iterable<Task> iterate() {
      return this.tasks;
    }
    
    /* @todo #12 how do i add a task to a immutable iterable?
     * 
     */
    public Task add(String description) {
    	tasks.add(description);
    	return task;
    }

}
