package ui.sticky;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.font.SimpleEnvelopeWithFont;
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
	
	/*
	 * @todo #25 extract default size
	 */
	private final Dimension defaultSize = new Dimension(200, 150);
	
	/*
	 * @todo #25 extract default position
	 */
	private final Point defaultPosition = new Point(
			(Toolkit.getDefaultToolkit().getScreenSize().width - defaultSize.width )/ 2 , 
			(Toolkit.getDefaultToolkit().getScreenSize().height - defaultSize.height )/ 2);

	/*
	 * @todo #24 extract default color
	 */
	private final Color defaultColor = new Color(251,247,174);

	/*
	 * @todo #24 extract default font
	 */
	private final Font defaultFont = new Font("Segoe UI", 0, 12);
	
	private final String originalText; 

	private final JDialog jdialog;
	private final JPopupMenu popup;
	private final JTextArea txtArea;
	private final Application application;
	private final JMenuItem colorMenuItem;
	private final JMenuItem saveMenuItem;
	private final JMenuItem deleteMenuItem;
	private final Envelope envelope;

	public JDialogSimpleSticky(Envelope envelope, Application application) {
		this.application = application;
		this.envelope = envelope;
		
		this.jdialog = new JDialog();
		this.jdialog.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));

		this.popup = new JPopupMenu();
		
		/* 
		 * @todo #88 add automatic persistence on moving / resizing
		 */
		//this.jdialog.addComponentListener(new JDialogStickyComponentListener());
		
		//formatting the textarea with default values
		this.txtArea = new JTextArea(envelope.text());
		this.originalText = envelope.text();
		this.txtArea.setLineWrap(true);
		
		this.txtArea.setFont(defaultFont);
		
        this.txtArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.jdialog.setLocation(defaultPosition);

        this.txtArea.setBackground(defaultColor);
        
        //adding the textarea
        this.jdialog.getContentPane().add(this.txtArea, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtArea.setComponentPopupMenu(popup);
		
        //adding actionlistener to text area
//        this.txtDescription.addFocusListener(new SimpleEnvelopeFocusListener(this));
        
        //setting the popup menu to show color select option
        colorMenuItem = new JMenuItem("Color...");
        
        //adding action to color menu
        colorMenuItem.addActionListener(new ActionListener() {
			
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//show a colorchooser
        		Color newColor = JColorChooser.showDialog(null,
                        "Choose Color",
                        new Color(251,247,174)
                        );
        		if (newColor != null) {
        			//save color to envelope
        			txtArea.setBackground(newColor);
//        			application.colorFactory().create(new SimpleEnvelopeWithColor(envelope, newColor));
        		}
        	}
		});		
        
        //setting the popup menu to show save option
        saveMenuItem = new JMenuItem("Save");
        
        //adding listener to save text information via menu
      //if something changed, save what changed (simple mode, no autosaving)
        saveMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!txtArea.getText().equals(originalText)) {
					envelope.text(txtArea.getText());
				}
				if (!jdialog.getLocation().equals(defaultPosition)) {
					application.positionFactory().create(new SimpleEnvelopeWithPosition(envelope, jdialog.getLocation()));
				} 
				if (!jdialog.getSize().equals(defaultSize)) {
					application.sizeFactory().create(new SimpleEnvelopeWithSize(envelope, jdialog.getSize()));
				} 
				if (!txtArea.getBackground().equals(defaultColor)) {
					application.colorFactory().create(new SimpleEnvelopeWithColor(envelope, txtArea.getBackground()));
				} 
				if (!txtArea.getFont().equals(defaultFont)) {
					application.fontFactory().create(new SimpleEnvelopeWithFont(envelope, txtArea.getFont()));
				} 
			}
		});
        
        //setting the popup menu to show delete option
        deleteMenuItem = new JMenuItem("Delete");
        
        //show confirmation and delete this note and its envelopes
        deleteMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(jdialog, "Are you sure you want to delete this note?", 
					       "Delete note", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					envelope.delete();
					jdialog.dispose();
				}
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
//				application.fontFactory().create(new SimpleEnvelopeWithFont(envelope, newFont));
			}
		});        
        
        popup.add(colorMenuItem);
        popup.add(fontMenu);
        popup.add(saveMenuItem);
        popup.add(deleteMenuItem);
			
        jdialog.pack();		
		jdialog.setSize(defaultSize);
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
	public JMenuItem deleteItem() {
		return this.deleteMenuItem;
	}
}
