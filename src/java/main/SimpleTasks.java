package main;

/**
 * <p> A simple implementation of {@link Tasks}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleTasks implements Tasks{
	
	private Tasks tasks;
	
	public SimpleTasks(Tasks tasks) {
		this.tasks = tasks;
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate();
	}

	@Override
	public Tasks add(Task task) {
		return new SimpleTasks(tasks).add(task);
	}

}
