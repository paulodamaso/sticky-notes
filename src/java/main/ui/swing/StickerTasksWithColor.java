package main.ui.swing;

import main.Task;
import main.ui.TaskWithColor;
import main.ui.TasksWithColor;

public final class StickerTasksWithColor implements StickerTasks<StickerTaskWithColor> {
	
	private final StickerTasks<StickerTaskWithColor> tasks;
	
	public StickerTasksWithColor(StickerTasks<StickerTaskWithColor> tasks) {
		this.tasks = tasks;
	}


	@Override
	public void print() {
		for(StickerTaskWithColor task : iterate()) {
			task.print();
		}
		
	}

	@Override
	public Iterable<StickerTaskWithColor> iterate() {
		return tasks.iterate();
	}

	@Override
	public StickerTaskWithColor add(StickerTaskWithColor task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StickerTaskWithColor add(String description) {
		// TODO Auto-generated method stub
		return null;
	}

}
