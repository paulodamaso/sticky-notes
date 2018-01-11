package ui.console;

import main.envelope.Envelope;
import ui.PrintMedia;

/**
 * <p> Prints {@link Envelope} to console.
 * 
 * @author paulodamaso
 *
 */
public final class ConsoleSimpleMedia implements PrintMedia {
	
	private final Envelope envelope;
	
	public ConsoleSimpleMedia(Envelope envelope) {
		this.envelope = envelope;
	}
	
	@Override
	public void print() {
		System.out.println(envelope.id() + " - This is a note with the text: \"" + envelope.text()+"\"");		
	}
}
