package main.note;

import java.util.Collection;

/**
 * <p> {@link Note} collection.
 * 
 * @author paulodamaso
 *
 */
public interface Notes  {
	
	public Collection<Note> iterate();
	
	public Note add(String text);
	
}
