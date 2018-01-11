package ui.sticky;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.Application;
import main.envelope.Envelope;
import main.envelope.position.SimpleEnvelopeWithPosition;
import main.envelope.size.SimpleEnvelopeWithSize;
import ui.SimpleMedia;

/**
 * <p> A sticky implementation of a {@link Envelope} using {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public class JDialogSimpleSticky implements SimpleMedia, JDialogSticky {

	private final JDialog jdialog;
	private final JPopupMenu popup;
	private final JTextArea txtArea;
	private final Application application;
	private final JMenuItem colorMenuItem;
	private final JMenuItem saveMenuItem;
	private final Envelope envelope;

	public JDialogSimpleSticky(Envelope envelope, Application application) {
		this.application = application;
		this.envelope = envelope;
		
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
        colorMenuItem = new JMenuItem("Color...");
        
        //adding action to color menu
        colorMenuItem.addActionListener(new ColorActionListener(envelope, application));		
        
        //setting the popup menu to show save option
        saveMenuItem = new JMenuItem("Save");
        
        //adding listener to save text information via menu
        saveMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//if something changed, save what changed (simple mode, no autosaving)
				System.out.println(jdialog.getLocation());
				if (jdialog.getLocation().getY() != 0 && jdialog.getLocation().getX() != 0) {
					Envelope  env = application.positionFactory().create(new SimpleEnvelopeWithPosition(envelope, jdialog.getLocation()));
				} else if (jdialog.getSize().getHeight() != 150 && jdialog.getSize().getWidth() != 300) {
					Envelope  env = application.sizeFactory().create(new SimpleEnvelopeWithSize(envelope, jdialog.getSize()));
				} //else if ()
				save();
			}
		});
        
     
        //setting the popup menu to show font select option
        JMenuItem fontMenu = new JMenuItem("Font...");
        //adding action to font menu
        fontMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Font newFont = NwFontChooserS.showDialog(null, "Choose font", txtArea.getFont());
				txtArea.setFont(newFont);
			}
		});        
        
        popup.add(colorMenuItem);
        popup.add(fontMenu);
        popup.add(saveMenuItem);
			
        jdialog.pack();		
	}

	@Override
	public JDialog jDialog() {
		return this.jdialog;
	}
	
	@Override
	public Application application() {
		return this.application;
	}

	@Override
	public JTextArea txtArea() {
		return this.txtArea;
	}

	@Override
	public Envelope envelope() {
		return this.envelope;
	}

	@Override
	public JMenuItem saveItem() {
		return this.saveMenuItem;
	}

	@Override
	public void save() {
		System.out.println("Saved " + this.getClass());
	}	
}
