package main.ui.swing.jdialog.old;

import java.awt.Color;

import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;

/**
 * <p> {@link StickerWithColor} implementation in a {@link JTextArea} with color set.
 * 
 * @author paulodamaso
 *
 */
public class TextStickerWithColor implements TextSticker, StickerWithColor {

	private final TextSticker origin;
	
	public TextStickerWithColor(TextSticker originJDialog, Color color) {
		System.out.println("New textwithcolor");
		this.origin = originJDialog;
		
		//formatting the textarea with default values
        textArea().setBackground(color);
 
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
		return textArea().getBackground();
	}

	@Override
	public JTextArea textArea() {
		return origin.textArea();
	}

}
