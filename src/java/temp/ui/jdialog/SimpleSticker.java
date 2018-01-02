package temp.ui.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.note.Note;
import temp.envelope.jdialog.JDialogColorActionListener;
import temp.ui.jdialog.font.derby.StickerFontActionListener;
import temp.ui.jdialog.position.derby.StickerPositionActionListener;
import temp.ui.jdialog.size.derby.StickerSizeActionListener;

/**
 * <p> A simple {@link Note} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleSticker implements JDialogSticker {
	
	private final JDialog jdialog;
	private final JTextArea txtDescription;
	private final JPopupMenu popup;
	private final Note sticker;
	private final JMenuItem saveItem;

	
	public SimpleSticker(Note sticker) {
		this.jdialog = new JDialog();
		this.popup = new JPopupMenu();
		this.sticker = sticker;
		
		//formatting the textarea with default values
		this.txtDescription = new JTextArea(sticker.text());
		this.txtDescription.setLineWrap(true);
		/*
		 * @todo #25 extract default size
		 */
        this.txtDescription.setSize(300, 150);
        this.txtDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        
		/*
		 * @todo #24 extract default color
		 */
        this.txtDescription.setBackground(new Color(251,247,174));
        
        //adding the textarea
        this.jdialog.getContentPane().add(this.txtDescription, BorderLayout.CENTER);
        
        //setting the default popup menu (empty)
		this.txtDescription.setComponentPopupMenu(popup);
		
        //adding actionlistener to text area
        this.txtDescription.addFocusListener(new SimpleStickerFocusListener(this));
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Color...");
        //adding action to color menu
        colorMenu.addActionListener(new JDialogColorActionListener(this));
        
        //setting the popup menu to show save option
        this.saveItem = new JMenuItem("Save");
        
        //adding listener to check if it had changed position to save with position via menu
        /*
         * @todo #26 very disgusting way of propagating persist behavior called from menuitem in JDialogStickerWithPosition
         *  had to do this way because i don't want to save the sticker object in each decoration of jdialogsticker
         */        
        this.saveItem.addActionListener(new StickerPositionActionListener(this));
        
        //adding listener to check if it had changed size to save with new size via menu
        /*
         * @todo #25 very disgusting way of propagating persist behavior called from menuitem in JDialogStickerWithSize
         *  had to do this way because i don't want to save the sticker object in each decoration of jdialogsticker
         */        
        this.saveItem.addActionListener(new StickerSizeActionListener(this));
        
        //adding listener to detect if this sticker had moved or resized
        this.jdialog().addComponentListener(new StickerComponentListener(this));
        
        /*
         * @todo #12 each decoration added (actionlistener) saves the sticker one more time; it should be saved just once
         */
        
        //setting the popup menu to show font select option
        JMenuItem fontMenu = new JMenuItem("Font...");
        //adding action to font menu
        fontMenu.addActionListener(new StickerFontActionListener(this));        
        

        
        popup().add(colorMenu);
        popup().add(fontMenu);
        popup().add(this.saveItem);
		
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
		return txtDescription.getText();
	}

	@Override
	public Note persist(Note sticker) {
		//System.out.println("Persisting SimpleSticker " + id());
		return this.sticker.persist(this);
	}
	
	@Override
	public JTextArea txtDescription() {
		return txtDescription;
	}

	@Override
	public JPopupMenu popup() {
		return popup;
	}

	@Override
	public JDialog jdialog() {
		return jdialog;
	}

	@Override
	public JMenuItem saveItem() {
		return this.saveItem;
	}
}
