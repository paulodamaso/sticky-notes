package main.ui.swing;

import main.Task;
import main.Tasks;
import main.ui.PrintableTask;
import main.ui.PrintableTasks;

/**
 * <p> {@link SimpleSticker} collection of {@link Task} 
 * 
 * @author paulodamaso
 *
 */
public final class SimpleStickerTasks implements StickerTasks<SimpleStickerTask> {
	
	private final PrintableTasks<PrintableTask> tasks;

	public SimpleStickerTasks(PrintableTasks<PrintableTask> tasks) {
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
		for(PrintableTask tsk : iterate()) {
			try {
				tsk.print();
			}catch (ClassCastException cce) {
				//it is not a printable task; make it a sticker task and print it 
				new SimpleStickerTask(tsk).print();
			}
		}
	}

	@Override
	public Iterable<SimpleStickerTask> iterate() {
		return tasks.iterate(); 
	}

	@Override
	public Task add(String description) {
		return tasks.add(description);
	}

}
