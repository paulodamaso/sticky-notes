package main.ui.swing;

import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.ui.swing.sticker.Sticker;

/**
 * <p> {@link Sticker} whose font can be set. 
 * 
 * @author paulodamaso
 *
 */
public final class TaskWithFont extends Sticker {
	
	private final Sticker task;

	public TaskWithFont(Sticker task, Font font) {
		super(task);
		this.task = task;
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
