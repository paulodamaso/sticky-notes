package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextArea;


/**
 * <p> A {@link Task} modeled like a Post-It sticker.
 * 
 * @author paulodamaso
 *
 */
public final class PostItTask extends JFrame implements Task, Printable {
	
	private final JTextArea txtDescription;
	private final Task task;
	
	public PostItTask(Task task) {
		super();
		this.task = task;
		this.txtDescription = new JTextArea(description());
		this.txtDescription.setLineWrap(true);
        Font font = new Font("Segoe Script", Font.BOLD, 20);
        this.txtDescription.setFont(font);
        this.txtDescription.setBackground(new Color(204, 194, 16));
        this.txtDescription.setSize(300, 150);
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
