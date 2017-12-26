package main.sticker.color;

import java.awt.Color;

import main.sticker.Sticker;

/**
 * <p> Decorator to add color to a {@link Sticker}.
 * @author paulodamaso
 *
 */
public interface StickerWithColor extends Sticker{
	
	public abstract Color color();

}
