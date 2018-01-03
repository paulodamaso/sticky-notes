package main.envelope;

import main.envelope.media.PrintMedia;
import main.note.Note;

/**
 * <p> A envelope decorator responsible for {@link Note} presentation.
 * 
 * @author paulodamaso
 *
 */
public interface Envelope extends Note {
	
	/**
	 * <p> Returns the {@link PrintMedia} in which this object is printed.
	 * @param media
	 * @return
	 */
	public PrintMedia media ();
	
}
