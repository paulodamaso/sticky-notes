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

/**
 * <p> A simple {@link Sticker}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleSticker implements Sticker {
	
	private final JDialog sticker;
	private final JTextArea txtDescription;
	private final JPopupMenu popup;
	private final Task task;
	
	public SimpleSticker(Task task) {
		this.sticker = new JDialog();
		this.task = task;
		this.popup = new JPopupMenu();
		
		//formatting the textarea with default values
		this.txtDescription = new JTextArea(task.description());
		this.txtDescription.setLineWrap(true);
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.txtDescription.setBackground(new Color(251,247,174));
        
        //adding the textarea
        sticker.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtDescription.setComponentPopupMenu(popup);
		
        //adding action to text area
        this.txtDescription.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	persist(SimpleSticker.this);
            }

        });
		
        sticker.pack();
	}

	@Override
	public void print() {
		if (!sticker.isVisible()) {
			sticker.setVisible(true);
		}
	}
	
	public JPopupMenu popUpMenu() {
		return this.popup;
	}
	
	/*
	 * see derbystickerswithcolor line 46.
	 */
	public Task task() { 
		return this.task;
	} 

	@Override
	public JTextArea description() {
		return txtDescription;
	}

	@Override
	public void persist(Sticker sticker) {
		// TODO Auto-generated method stub
		
	}
	
	public void persist(SimpleSticker sticker) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void persist(SimpleSticker sticker) {
//		//lousy sticker, does not have position, color, nothing; should just save task info
//		//this.task.persist(task), looks strange, but typecasting?!
//		//you're a criminal!
//		try {
//			System.out.println("SimpleSticker persisting task!");
//			((Persistent<Task>)task).persist(task);
//			return this;
//		} catch (Exception e) {
//			System.out.println("You can't save this task!");
//		}
//		return null;
//	}


}
