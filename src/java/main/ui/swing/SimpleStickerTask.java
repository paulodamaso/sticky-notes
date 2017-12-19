package main.ui.swing;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.PrintableTask;
import main.Task;


/**
 * <p> A {@link Task} modeled like a Post-It sticker.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, font style, backcolor) 
 */
public class SimpleStickerTask extends JDialog implements StickerTask {
	
	private final JTextArea txtDescription;
	private final JPopupMenu popup;
	private final Task task;
	
	public SimpleStickerTask(Task task) {
		super();
		this.task = task;
		this.popup = new JPopupMenu();
		
		//formatting the textarea with default values
		this.txtDescription = new JTextArea(description());
		this.txtDescription.setLineWrap(true);
//        this.txtDescription.setFont(new Font("Segoe Script", Font.BOLD, 20));
//        this.txtDescription.setBackground(new Color(204, 194, 16));
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //adding the textarea
        this.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
        
        //setting position
//        this.setLocation(0, 0);
        
        //setting the popup menu
		this.txtDescription.setComponentPopupMenu(popup);
		
		this.pack();
	}

	@Override
	public void print() {
		if (!this.isVisible()) {
			this.setVisible(true);
		}
	}

	@Override
	public int id() {
		return task.id();
	}

	@Override
	public String description() {
		return task.description();
	}
	
	public JTextArea txtDescription() {
		return this.txtDescription;
	}
	
	public JPopupMenu popUpMenu() {
		return this.popup;
	}

}
