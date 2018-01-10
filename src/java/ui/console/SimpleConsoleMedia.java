package ui.console;

import main.envelope.Envelope;
import ui.SimpleMedia;

/**
 * <p> Prints {@link Envelope} to console.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleConsoleMedia implements SimpleMedia {

	@Override
	public void print(Envelope envelope) {
		System.out.println(envelope.id() + " - This is a note with text: \"" + envelope.text()+"\"");		
	}
}
