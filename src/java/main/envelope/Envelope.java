package main.envelope;

import main.note.Note;

/**
 * <p> A envelope decorator responsible for {@link Note} presentation.
 * 
 * @author paulodamaso
 *
 */
public interface Envelope extends Note {
	
	public void print();
}
