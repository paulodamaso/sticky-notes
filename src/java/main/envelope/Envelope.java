package main.envelope;

import main.note.Note;
import ui.PrintMedia;

/**
 * <p> A envelope decorator responsible for {@link Note} presentation.
 * 
 * @author paulodamaso
 *
 */
public interface Envelope extends Note {
	
	/**
	 * <p> Defines the {@link PrintMedia} in which this object is printed.
	 * 
	 * @param media
	 * @return
	 */
	public default void print(PrintMedia media) {
		media.print(this);
	}
	
	public Envelope origin();
	
	public default void printDecorations(Envelope envelope) {

		if (envelope == null) {
			System.out.println("Last");
		} else {
			System.out.println(envelope);
			printDecorations(envelope.origin());
		}

	} 
	
}
