package main.ui.swing.sticker;

import main.Task;
import main.ui.Printable;


/**
 * <p> A top level representation of a {@link Task} modeled like a Post-It sticker.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, font style, backcolor) 
 */
public interface Sticker extends Printable, Persistent {

	public abstract int id();
	
	public abstract String text();

}
