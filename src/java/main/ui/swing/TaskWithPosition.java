package main.ui.swing;

import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.ui.swing.sticker.Sticker;

/**
 * <p> {@link Sticker} whose position can be set.
 * 
 * @author paulodamaso
 *
 */
public final class TaskWithPosition extends Sticker {
	
	private final Sticker task;

	public TaskWithPosition(Sticker task, int x, int y) {
		super(task);
		this.task = task;
		
		//setting the location
		this.task.setLocation(x, y);
	}
	
	@Override
	public int id() { return task.id(); }

	@Override
	public String description() {return task.description();	}
	
	@Override
	public void print() {
		task.print();
	}

	@Override
	public JTextArea txtDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPopupMenu popUpMenu() {
		// TODO Auto-generated method stub
		return null;
	}
}
