package main;

public final class PostItTasks implements Tasks, Printable {
	
	private final Tasks tasks;

	public PostItTasks(Tasks tasks) {
		super();
		this.tasks = tasks;
	}

	@Override
	public void print() {
		for(Task tsk : iterate()) {
			new PostItTask(tsk).print();
		}
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate(); 
	}

	@Override
	public Tasks add(Task task) {
		return new PostItTasks(tasks).add(task);
	}

}
