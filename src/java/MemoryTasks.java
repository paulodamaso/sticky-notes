public final class MemoryTasks implements Tasks {
    private final Iterable<Task> tasks;
    
    public MemoryTasks (Iterable<Task> tasks) {
      this.tasks = new ArrayList<Task>(tasks);
    }
    
    public Iterable<Task> iterate() {
      return this.tasks;
    }
    public Task add(Task task) {
      tasks = (new ArrayList<Task>(tasks).add(task));
      return task;
    }
}
