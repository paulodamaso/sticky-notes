package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


/**
 * <p> A {@link Task} modeled like a Post-It sticker.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, fonr style, backcolor) 
 */
/* @todo #6 remove minimize and maximize buttons.
 * 
 */
public final class PostItTask extends JFrame implements Task, Printable {
	
	private final JTextArea txtDescription;
	private final Task task;
	
	public PostItTask(Task task) {
		super();
		this.task = task;
		
		//formatting the textarea
		this.txtDescription = new JTextArea(description());
		this.txtDescription.setLineWrap(true);
        Font font = new Font("Segoe Script", Font.BOLD, 20);
        this.txtDescription.setFont(font);
        this.txtDescription.setBackground(new Color(204, 194, 16));
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //adding the textarea
        this.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
	}

	@Override
	public void print() {
		this.pack();
		this.setVisible(true);
	}

	@Override
	public int id() {
		return task.id();
	}

	@Override
	public String description() {
		return task.description();
	}

}
