package main.sticker.position;

import java.awt.Point;

import main.sticker.Sticker;

/**
 * <p> A decorator to add position to a {@link Sticker}.
 * 
 * @author paulodamaso
 *
 */
public interface StickerWithPosition extends Sticker {

	public Point position();
	
}
