package main.sticker;

import main.sticker.persistence.Persistent;


/**
 * <p> A top level representation of a sticky note.
 * 
 * @author paulodamaso
 *
 */
public interface Sticker extends Persistent{

	public abstract int id();
	
	public abstract String text();

}
