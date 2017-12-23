package main.ui.swing.sticker;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.Task;
import main.ui.Printable;


/**
 * <p> A top level representation of a {@link Task} modeled like a Post-It sticker using {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
/* @todo #6 it would be interesting to allow the use to change some features (font, font size, font style, backcolor) 
 */
/* @todo #12 should be an interface not an abstract class
 *  it was intended to make an interface, but i couldn't find JDialog interface
 *  made an interface, using abstract class was giving too much trouble
 * 
 */
public interface Sticker extends Printable {

	public abstract JTextArea description();
	
	public abstract JPopupMenu popUpMenu() ;
	
	/*
	 * see derbystickerswithcolor line 46.
	 */
	public abstract Task task();

//	public Sticker persist();
}
