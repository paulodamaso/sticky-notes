package main.ui.swing;

import main.Printable;
import main.PrintableTask;
import main.Task;
import main.Tasks;

/**
 * <p> A {@link Tasks} representation of {@link PostItTask}.
 * 
 * @author paulodamaso
 *
 */
/* 
 * @todo #6 still smelly, it's a bridge in disguise: PostItTasks only handle PostItTask
 * 
 */
public final class PostItTasks implements Tasks, Printable {
	
	private final Tasks tasks;

	public PostItTasks(Tasks tasks) {
		super();
		this.tasks = tasks;
	}

	@Override
	public void print() {
		for(Task tsk : iterate()) {
			((PrintableTask)tsk).print();
		}
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate(); 
	}

	@Override
	public Task add(Task task) {
		return new PostItTask(task);
	}

}
