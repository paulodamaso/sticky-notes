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
	public Task add(String description) {
		return tasks.add(description);
	}

}
