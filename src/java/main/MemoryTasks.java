package main;

import java.util.ArrayList;
import java.util.Collection;

public final class MemoryTasks implements Tasks {
    private final Iterable<Task> tasks;
    
    public MemoryTasks (Iterable<Task> tasks) {
      this.tasks = new ArrayList<Task>((Collection<Task>)tasks);
    }
    
    public Iterable<Task> iterate() {
      return this.tasks;
    }
    
    
    /*
     * @TODO #0001 arrumar essa implementação sem usar classe anônima
     */
    public Tasks add(Task task) {
    	return new MemoryTasks(new ArrayList<Task>((Collection<Task>)tasks) {{add(task);}});
    }
}
