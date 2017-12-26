package main.sticker.ui.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.sticker.Sticker;
import main.sticker.ui.jdialog.color.derby.StickerColorActionListener;
import main.sticker.ui.jdialog.persistence.StickerSaveActionListener;

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
	private Sticker sticker;
	
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
		
        //adding actionlistener to text area
        //this.txtDescription.addFocusListener(new StickerFocusListener(this));
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Cor...");
        
        //adding action to color menu
        colorMenu.addActionListener(new StickerColorActionListener(this));
        
        //setting the popup menu to show save option
        JMenuItem saveMenu = new JMenuItem("Salvar");
        saveMenu.addActionListener(new StickerSaveActionListener(this));
        
        //adding action to color menu
//        colorMenu.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//			}
//		});

        
        popup().add(colorMenu);
        popup().add(saveMenu);
		
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
	public Sticker persist(Sticker sticker) {
		System.out.println("Persisting SimpleSticker " + id());
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

}
