package main.sticker.size;

import java.awt.Dimension;

import main.sticker.Sticker;

/**
 * <p> A decorator to add size information to a {@link Sticker}.
 * @author paulodamaso
 *
 */
public interface StickerWithSize extends Sticker {

	public abstract Dimension size();
}
