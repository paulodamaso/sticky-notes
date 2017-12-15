package main;

import java.util.Date;

/**
 * <p> A {@link Task} which prints itself to console.
 * 
 * @author paulodamaso
 *
 */
public final class ConsoleTask implements Task, Printable {
	
	private final Task task;
	
	public ConsoleTask(Task task) {
		this.task = task;
	}

	@Override
	public void print() {
		System.out.println(id() +" - " + description());
	}

	@Override
	public int id() {
		return task.id();
	}

	@Override
	public String description() {
		return task.description();
	}

	@Override
	public Date date() {
		return task.date();
	}

}
