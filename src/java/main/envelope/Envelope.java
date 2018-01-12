package main.envelope;

import main.note.Note;
import ui.PrintMedia;

/**
 * <p> A envelope decorator for {@link Note} presentation.
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
		media.print();
	}
	

	/**
	 * <p> Reference to the {@link Envelope} decorated by this {@link Envelope}.
	 * @return
	 */
	public Envelope origin();
	
	/**
	 * <p> Recursively iterate through decorated {@link Envelopes}.
	 * 
	 * @param envelope
	 */
	public default void printDecorations(Envelope envelope) {

		if (envelope == null) {
			System.out.println("Last");
		} else {
			System.out.println(envelope);
			printDecorations(envelope.origin());
		}

	} 
	
}
