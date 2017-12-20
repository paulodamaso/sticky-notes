package main.ui.swing;

import main.PrintableTask;
import main.PrintableTasks;
import main.Task;
import main.Tasks;

/**
 * <p> A {@link Tasks} representation of {@link SimpleStickerTask}.
 * 
 * @author paulodamaso
 *
 */
public final class StickerTasks implements PrintableTasks {
	
	private final Tasks tasks;

	public StickerTasks(Tasks tasks) {
		super();
		this.tasks = tasks;
	}

	/*
	 * @todo #6 this implementation does not convince me yet, it needs to be tested
	 *  with decorated simplestickertask
	 */
	@Override
	public void print() {
		for(Task tsk : iterate()) {
			new SimpleStickerTask(tsk).print();
		}
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
