package main.ui.swing.sticker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.Task;
import main.persistence.PersistentTask;
import main.ui.Printable;


/**
 * <p> A {@link Task} modeled like a Post-It sticker using {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, font style, backcolor) 
 */
public class Sticker extends JDialog implements Printable {
	
	protected final JTextArea txtDescription;
	protected final JPopupMenu popup;
	protected final Task task;
	
	public Sticker(Task task) {
		super();
		this.task = task;
		this.popup = new JPopupMenu();
		
		//formatting the textarea with default values
		this.txtDescription = new JTextArea(task.description());
		this.txtDescription.setLineWrap(true);
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.txtDescription.setBackground(new Color(251,247,174));
        
        //adding the textarea
        this.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtDescription.setComponentPopupMenu(popup);
		
        //adding action to color menu
        this.txtDescription.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	save();
            }

        });
		
		this.pack();
	}

	@Override
	public void print() {
		if (!this.isVisible()) {
			this.setVisible(true);
		}
	}
	
	public JTextArea txtDescription() {
		return this.txtDescription;
	}
	
	public JPopupMenu popUpMenu() {
		return this.popup;
	}
	
	public Task task() { 
		return this.task;
	} 
	
	public void save() {
		//lousy sticker, does not have position, color, nothing; should just save task info
		//this.task.save ?
		//you're a criminal!
		try {
			((PersistentTask)task).save();
		} catch (Exception e) {
			System.out.println("You can't save this task!");
		}
	}

}
