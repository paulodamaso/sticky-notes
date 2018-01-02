package main.ui;

import java.awt.Color;

import main.envelope.color.EnvelopeWithColor;
import main.note.Note;

/**
 * <p> Console media for printing {@link Note}.
 * 
 * @author paulodamaso
 *
 */
public class ConsoleMedia implements PrintMedia<Note> {
	
	private Note sticker;
	
	public ConsoleMedia(Note sticker) {
		this.sticker = sticker;
	}

	@Override
	public PrintMedia<Note> with(String k, String v) {
		if (k.equalsIgnoreCase("color")) {
			return new ConsoleMedia(new SimpleStickerWithColor(sticker, v));
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note output() {
		System.out.println("");
		return null;
	}
	
}
