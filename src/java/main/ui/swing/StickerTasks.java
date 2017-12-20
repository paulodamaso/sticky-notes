package main.ui.swing;

import main.Printable;
import main.Task;
import main.Tasks;

/**
 * <p> A {@link Tasks} representation of {@link SimpleStickerTask}.
 * 
 * @author paulodamaso
 *
 */
public final class StickerTasks implements Printable, Tasks {
	
	private final Tasks tasks;

	public StickerTasks(Tasks tasks) {
		super();
		this.tasks = tasks;
	}

	/*
	 * @todo #6 this implementation does not convince me yet, it needs to be tested
	 *  with decorated simplestickertask saved to database
	 */	
	
	/* 
	 * @ todo #23 awful way to avoid ClassCastExceptions when we have a non-printable task and
	 *  avoid losing decorations when we have a decorated StickerTask. i dunno what behavior
	 *  should happen here; probably it'll be decided when deciding how to store StickerTask visual properties
	 */
	@Override
	public void print() {
		for(Task tsk : iterate()) {
			try {
			((StickerTask)tsk).print();
			}catch (ClassCastException cce) {
				//it is not a sticker task; make it a sticker task and print it 
				new SimpleStickerTask(tsk).print();
			}
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
