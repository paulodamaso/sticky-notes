package main.note;

import java.util.Collection;

/**
 * <p> {@link Note} collection.
 * 
 * @author paulodamaso
 *
 */
public interface Notes  {
	
	public Collection<Note> notes();
	
	public Note add(String text);
	
	public void remove (Note note);
	
}
