package main.ui.swing.jdialog;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;

public class JDialogStickerWithColor implements JDialogSticker, StickerWithColor {

	private final JDialogSticker origin;
	
	public JDialogStickerWithColor(JDialogSticker originJDialog, Color color) {
		System.out.println("New jdialogwithcolor");
		this.origin = originJDialog;
		
		//formatting the textarea with default values
        txtText().setBackground(color);
 
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public Sticker persist(Sticker sticker) {
		return origin.persist(this);
	}

	@Override
	public Color color() {
		return txtText().getBackground();
	}

	@Override
	public JTextArea txtText() {
		return origin.txtText();
	}

	@Override
	public JDialog jdialog() {
		return origin.jdialog();
	}

}
