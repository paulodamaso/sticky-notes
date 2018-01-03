package main.envelope.media.console;

import main.envelope.Envelope;
import main.envelope.media.SimpleMedia;

/**
 * <p> Prints {@link Envelope} to console.
 * @author paulodamaso
 *
 */
public final class SimpleConsoleMedia implements SimpleMedia {
	
	private final Envelope origin;

	public SimpleConsoleMedia(Envelope envelope) {
		this.origin = envelope;
	}

	@Override
	public void print() {
		System.out.println(origin.id() + " - This is a note with text: \"" + origin.text()+"\"");
	}
}
