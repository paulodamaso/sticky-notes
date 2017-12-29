package main.ui.swing.jdialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.sticker.Sticker;

public class SimpleDialogSticker implements JDialogSticker {
	
	private final Sticker origin;
	private final JTextArea txtText;
	private final JDialog jdialog;

	
	public SimpleDialogSticker(Sticker origin) {
		System.out.println("New simpledialog");
		this.origin = origin;
		this.jdialog = new JDialog();
		
		//formatting the textarea with default values
		txtText = new JTextArea(origin.text());
		txtText.setLineWrap(true);

        txtText.setSize(300, 150);
        txtText.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        txtText.setBackground(new Color(251,247,174));
        
        //adding the textarea
        jdialog.getContentPane().add(txtText, BorderLayout.CENTER);
        

		
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Color...");
        //adding action to color menu
//        colorMenu.addActionListener(new StickerColorActionListener(this));
        
        //setting the popup menu to show font select option
        JMenuItem fontMenu = new JMenuItem("Font...");
        //adding action to font menu
//        fontMenu.addActionListener(new StickerFontActionListener(this));    
        
        JPopupMenu popUpMenu = new JPopupMenu();
          
        popUpMenu.add(colorMenu);
        popUpMenu.add(fontMenu);
        
        //setting the default popup menu (empty)
		txtText.setComponentPopupMenu(popUpMenu);
		
        jdialog.pack();
        jdialog.setVisible(true);
	}

	@Override
	public Sticker persist(Sticker sticker) {
		return origin.persist(this);
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return txtText.getText();
	}

	@Override
	public JTextArea txtText() {
		return txtText;
	}

	@Override
	public JDialog jdialog() {
		return jdialog;
	}
}
