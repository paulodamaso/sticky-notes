package main.ui.swing.jdialog.old;

import java.awt.Color;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import main.sticker.Sticker;
import main.ui.PrintMedia;

/**
 * <p> A simple {@link TextSticker}, with just a {@link JTextArea}.
 * 
 * @author paulodamaso
 *
 */
public class SimpleTextSticker implements TextSticker {
	
	private final Sticker origin;
	private final JTextArea txtText;
	
	public SimpleTextSticker(Sticker origin) {
		System.out.println("New simplesticker");
		this.origin = origin;
		
		//formatting the textarea with default values
		txtText = new JTextArea(origin.text());
		txtText.setLineWrap(true);

        txtText.setSize(300, 150);
        txtText.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        txtText.setBackground(new Color(251,247,174));
        
		
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
	public JTextArea textArea() {
		return txtText;
	}

	@Override
	public void printed(PrintMedia media) {
		// TODO Auto-generated method stub
		
	}
}
