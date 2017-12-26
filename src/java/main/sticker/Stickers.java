package main.sticker;

import java.util.Collection;

/**
 * <p> {@link Sticker} collection.
 * 
 * @author paulodamaso
 *
 */
public interface Stickers  {
	
	public Collection<Sticker> iterate();
	
	public Sticker add(String sticker);

}
