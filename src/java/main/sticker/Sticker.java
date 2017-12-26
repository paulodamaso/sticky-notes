package main.sticker;

import main.sticker.persistence.Persistent;
import main.task.Task;


/**
 * <p> A top level representation of a {@link Task} modeled like a Post-It sticker.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, font style, backcolor) 
 */
public interface Sticker extends Persistent{

	public abstract int id();
	
	public abstract String text();

}
