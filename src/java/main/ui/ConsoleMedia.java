package main.ui;

import java.awt.Color;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;

/**
 * <p> Console media for printing {@link Sticker}.
 * 
 * @author paulodamaso
 *
 */
public class ConsoleMedia implements PrintMedia<Sticker> {
	
	private Sticker sticker;
	
	public ConsoleMedia(Sticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public PrintMedia<Sticker> with(String k, String v) {
		if (k.equalsIgnoreCase("color")) {
			return new ConsoleMedia(new SimpleStickerWithColor(sticker, v));
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sticker output() {
		System.out.println("");
		return null;
	}
	
}
