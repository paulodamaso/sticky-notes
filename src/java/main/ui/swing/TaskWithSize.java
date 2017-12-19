package main.ui.swing;

import main.PrintableTask;
import main.Task;

/**
 * <p> {@link Task} whose size can be set.
 * @author paulodamaso
 *
 */
public final class TaskWithSize implements PrintableTask {
	
	private final SimpleStickerTask task;

	public TaskWithSize(Task task, int width, int height) {

		this.task= new SimpleStickerTask(task);
		this.task.setSize(width, height);
	}
	
	@Override
	public int id() { return task.id(); }

	@Override
	public String description() {return task.description();	}
	
	@Override
	public void print() {
		task.print();
	}

}
