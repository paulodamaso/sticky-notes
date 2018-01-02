package main.envelope;

import main.note.Note;
import main.note.persistence.Persistent;

/**
 * <p> Presentation envelope for a {@link Envelope}.
 * 
 * @author paulo
 *
 */
public interface Envelope extends Persistent<Envelope>{

	public int id();
	public Note note();

}
