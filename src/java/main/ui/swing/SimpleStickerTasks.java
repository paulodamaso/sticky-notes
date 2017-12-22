package main.ui.swing;

import main.Task;
import main.Tasks;
import main.ui.Printable;
import main.ui.swing.sticker.Sticker;


/**
 * <p> {@link Sticker} collection of {@link Task} 
 * 
 * @author paulodamaso
 *
 */
public final class SimpleStickerTasks implements Tasks<Task> {
	
	private Tasks<Task> tasks;

	public SimpleStickerTasks(Tasks<? extends Task> tasks) {
		this.tasks = (Tasks<Task>) tasks;
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
		for(Task  tsk : iterate()) {
			try {
				((PrintableTask)tsk).print();
			}catch (ClassCastException cce) {
				//it is not a printable task; make it a sticker task and print it 
				new Sticker(tsk).print();
			}
		}
	}
	
	@Override
	public Sticker add(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate();
	}

}
