package main.ui.swing;

import main.Task;

/**
 * <p> {@link Task} whose size can be set.
 * @author paulodamaso
 *
 */
public final class TaskWithSize extends StickerTask {
	
	private final StickerTask task;

	public TaskWithSize(StickerTask task, int width, int height) {
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
