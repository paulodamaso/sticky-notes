package main.ui.swing;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.Task;

/**
 * <p> A {@link Task} whose color can be set.
 * 
 * @author paulodamaso
 *
 */
public final class TaskWithColor extends StickerTask {
	
	private final StickerTask task;
	
	public TaskWithColor (StickerTask task, Color color) {
		super(task);
		this.task = task;
		
		//adding color to the textarea
        txtDescription().setBackground(color);
        
        //setting the popup menu to show color select option
        popUpMenu().add(new JMenuItem("Cor..."));

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
