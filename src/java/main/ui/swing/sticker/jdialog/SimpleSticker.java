package main.ui.swing.sticker.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.ui.swing.sticker.Sticker;

/**
 * <p> A simple {@link Sticker} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleSticker implements JDialogSticker {
	
	private final JDialog jdialog;
	private final JTextArea txtDescription;
	private final JPopupMenu popup;
	private final Sticker sticker;
	
	public SimpleSticker(Sticker sticker) {
		this.jdialog = new JDialog();
		this.popup = new JPopupMenu();
		this.sticker = sticker;
		
		//formatting the textarea with default values
		this.txtDescription = new JTextArea(sticker.text());
		this.txtDescription.setLineWrap(true);
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.txtDescription.setBackground(new Color(251,247,174));
        
        //adding the textarea
        this.jdialog.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtDescription.setComponentPopupMenu(popup);
		
        //adding action to text area
        this.txtDescription.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	persist(SimpleSticker.this);
            }

        });
		
        jdialog.pack();
	}

	@Override
	public void print() {
		if (!jdialog.isVisible()) {
			jdialog.setVisible(true);
		}
	}

	@Override
	public int id() {
		return sticker.id();
	}

	@Override
	public String text() {
		return sticker.text();
	}

	@Override
	public Sticker persist(Sticker sticker) {
		System.out.println("Persisted simplesticker");
		return this.sticker.persist(sticker);
	}
	
	@Override
	public JTextArea txtDescription() {
		return txtDescription;
	}

	@Override
	public JPopupMenu popup() {
		return popup;
	}

}
