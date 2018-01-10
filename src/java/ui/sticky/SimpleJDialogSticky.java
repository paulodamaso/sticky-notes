package ui.sticky;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.SimpleEnvelopeWithColor;
import ui.PrintMedia;
import ui.SimpleMedia;

/**
 * <p> A sticky implementation of a {@link Envelope} using {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public class SimpleJDialogSticky implements SimpleMedia, JDialogSticky {

	private final JDialog jdialog;
	private final JPopupMenu popup;
	private final JTextArea txtArea;
	private final Application application;

	public SimpleJDialogSticky(Envelope envelope, Application application) {
		this.application = application;
		
		this.jdialog = new JDialog();
		jdialog.setTitle(envelope.getClass().toString());
		this.popup = new JPopupMenu();
		
		//formatting the textarea with default values
		this.txtArea = new JTextArea(envelope.text());
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
        colorMenu.addActionListener(new ColorActionListener(envelope, application));		
        
        //setting the popup menu to show save option
        JMenuItem saveItem = new JMenuItem("Save");
        
        //adding listener to save via menu
        saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//put text in envelope
				envelope.text(txtArea().getText());
			}
		});
        
        //adding listener to check if it had changed size to save with new size via menu
       
//        this.saveItem.addActionListener(new EnvelopeSizeActionListener(this));
        
        //adding listener to detect if this note had moved or resized
//        this.jDialog().addComponentListener(new EnvelopeComponentListener(this));
        
        //setting the popup menu to show font select option
        JMenuItem fontMenu = new JMenuItem("Font...");
        //adding action to font menu
        fontMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font newFont = NwFontChooserS.showDialog(null, "Choose font", txtArea().getFont());
				txtArea().setFont(newFont);
			}
		});        
        
        popUp().add(colorMenu);
        popUp().add(fontMenu);
        popUp().add(saveItem);
			
        jdialog.pack();		
	}

	@Override
	public JDialog jDialog() {
		return this.jdialog;
	}

	@Override
	public JPopupMenu popUp() {
		return this.popup;
	}

	@Override
	public JTextArea txtArea() {
		return this.txtArea;
	}

}
