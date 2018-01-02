package main.envelope.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.envelope.Envelope;
import main.note.Note;

public class SimpleJDialogEnvelope implements JDialogEnvelope {
	
	private final int id;
	private final JDialog jdialog;
	private final Note note;
	private final JPopupMenu popup;
	private final JTextArea txtArea;
	
	public SimpleJDialogEnvelope(Note note, int id) {
		this.id = id;
		this.note = note;
		this.jdialog = new JDialog();
		this.popup = new JPopupMenu();
		
		//formatting the textarea with default values
		this.txtArea = new JTextArea(note.text());
		this.txtArea.setLineWrap(true);
		/*
		 * @todo #25 extract default size
		 */
        this.txtArea.setSize(300, 150);
        this.txtArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        
		/*
		 * @todo #24 extract default color
		 */
        this.txtArea.setBackground(new Color(251,247,174));
        
        //adding the textarea
        this.jdialog.getContentPane().add(this.txtArea, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtArea.setComponentPopupMenu(popup);
		
        //adding actionlistener to text area
//        this.txtDescription.addFocusListener(new SimpleEnvelopeFocusListener(this));
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Color...");
        //adding action to color menu
        colorMenu.addActionListener(new JDialogColorActionListener(this));
        
        //setting the popup menu to show save option
        JMenuItem saveItem = new JMenuItem("Save");
        
        //adding listener to check if it had changed position to save with position via menu
        /*
         * @todo #26 very disgusting way of propagating persist behavior called from menuitem in JDialogEnvelopeWithPosition
         *  had to do this way because i don't want to save the note object in each decoration of jdialognote
         */        
//        this.saveItem.addActionListener(new EnvelopePositionActionListener(this));
        
        //adding listener to check if it had changed size to save with new size via menu
        /*
         * @todo #25 very disgusting way of propagating persist behavior called from menuitem in JDialogEnvelopeWithSize
         *  had to do this way because i don't want to save the note object in each decoration of jdialognote
         */        
//        this.saveItem.addActionListener(new EnvelopeSizeActionListener(this));
        
        //adding listener to detect if this note had moved or resized
//        this.jDialog().addComponentListener(new EnvelopeComponentListener(this));
        
        /*
         * @todo #12 each decoration added (actionlistener) saves the note one more time; it should be saved just once
         */
        
        //setting the popup menu to show font select option
        JMenuItem fontMenu = new JMenuItem("Font...");
        //adding action to font menu
//        fontMenu.addActionListener(new EnvelopeFontActionListener(this));        
        

        
        popUp().add(colorMenu);
        popUp().add(fontMenu);
        popUp().add(saveItem);
		
        jdialog.pack();
	}

	@Override
	public void print() {
		if (!jdialog.isVisible()) jdialog.setVisible(true);
	}

	@Override
	public JDialog jDialog() {
		return jdialog;
	}
	
	@Override
	public JPopupMenu popUp(){
		return this.popup;
	}

	@Override
	public JTextArea textArea() {
		return txtArea;
	}

	@Override
	public Note note() {
		return note;
	}

	@Override
	public Envelope persist(Envelope note) {
		return note.persist(note);
	}

	@Override
	public int id() {
		return this.id;
	}

}
