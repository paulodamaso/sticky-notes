package main.ui.swing.jdialog.old;

import java.awt.Font;

import javax.swing.JTextArea;

import main.envelope.font.StickerWithFont;
import main.note.Note;

public class TextStickerWithFont implements TextSticker, StickerWithFont {

	private final TextSticker origin;
	
	public TextStickerWithFont(TextSticker origin, Font font) {
		System.out.println("New jdialogwithfont");
		this.origin = origin;
        
        textArea().setFont(font);

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
	public Note persist(Note sticker) {
		return origin.persist(this);
	}

	@Override
	public Font font() {
		return textArea().getFont();
	}

	@Override
	public JTextArea textArea() {;
		return origin.textArea(); 
	}


}
