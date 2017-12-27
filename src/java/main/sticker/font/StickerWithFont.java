package main.sticker.font;

import java.awt.Font;

import main.sticker.Sticker;

/**
 * <p> A decorator to add font selection behavior to a {@link Sticker}.
 * 
 * @author paulodamaso
 *
 */
public interface StickerWithFont extends Sticker {

	public Font font();
}
