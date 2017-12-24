package main.ui.swing.sticker;

import main.ui.Printable;

/**
 * <p> {@link Sticker} collection.
 * 
 * @author paulodamaso
 *
 */
public interface Stickers extends Printable {
	
	public Iterable<Sticker> iterate();
	
	public Sticker add(String sticker);

}
