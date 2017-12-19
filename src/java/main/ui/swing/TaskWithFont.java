package main.ui.swing;

import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

/**
 * <p> {@link SimpleStickerTask} whose font can be set. 
 * 
 * @author paulodamaso
 *
 */
public final class TaskWithFont implements StickerTask {
	
	private final StickerTask task;

	public TaskWithFont(StickerTask task, Font font) {

		this.task = new SimpleStickerTask(task);
		txtDescription().setFont(font);
		
        //setting the popup menu to show font select option
		popUpMenu().add(new JMenuItem("Fonte..."));

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
		return this.task.txtDescription();
	}

	@Override
	public JPopupMenu popUpMenu() {
		return this.task.popUpMenu();
	}

}
