package temp.sticky;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.note.Note;
import temp.sticky.color.JDialogColorActionListener;
import temp.sticky.font.JDialogFontActionListener;

public final class SimpleStickyJDialogEnvelope implements StickyJDialogEnvelope {
	
	private final JDialog jdialog;
	private final Note note;
	private final JPopupMenu popup;
	private final JTextArea txtArea;

	public SimpleStickyJDialogEnvelope(Note note) {
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
        
        //adding listener to save via menu
        saveItem.addActionListener(new SaveActionListener(this));
        
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
        fontMenu.addActionListener(new JDialogFontActionListener(this));        
        

        
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
	public int id() {
		return note.id();
	}

	@Override
	public String text() {
		return note.text();
	}

	@Override
	public Note persist(Note persistent) {
		return note.persist(persistent);
	}
}
