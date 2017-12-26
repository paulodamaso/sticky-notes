package main.ui.swing;

import main.sticker.Sticker;
import main.task.Task;

/**
 * <p> {@link Task} whose size can be set.
 * @author paulodamaso
 *
 */
public final class TaskWithSize extends Sticker {
	
	private final Sticker task;

	public TaskWithSize(Sticker task, int width, int height) {
		super(task);
		this.task= task;
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
