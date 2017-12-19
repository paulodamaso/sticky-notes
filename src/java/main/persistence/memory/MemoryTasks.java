package main.persistence.memory;

import java.util.ArrayList;
import java.util.Collection;

import main.Task;
import main.Tasks;

/**
 * <p> {@link Tasks} interface which store data in memory. 
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
    	MemoryTask task = new MemoryTask(tasks.size(), description);
    	tasks.add(task);
    	return task;
    }

}
