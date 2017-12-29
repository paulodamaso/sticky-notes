package main.ui.swing.jdialog;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.sticker.font.StickerWithFont;

public class JDialogStickerWithFont implements JDialogSticker, StickerWithFont {

	private final JDialogSticker origin;
	
	public JDialogStickerWithFont(JDialogSticker origin, Font font) {
		System.out.println("New jdialogwithfont");
		this.origin = origin;
        
        txtText().setFont(font);

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
	public Font font() {
		return txtText().getFont();
	}

	@Override
	public JDialog jdialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTextArea txtText() {
		// TODO Auto-generated method stub
		return null;
	}
}
