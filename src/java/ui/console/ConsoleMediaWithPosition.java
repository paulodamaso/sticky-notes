package ui.console;

import java.awt.Point;

import main.envelope.position.EnvelopeWithPosition;
import ui.MediaWithPosition;
import ui.PrintMedia;

/**
 * <p> Print {@link EnvelopeWithPosition} to console.
 */
public final class ConsoleMediaWithPosition implements MediaWithPosition {

	private final EnvelopeWithPosition envelopeWithPosition;
	private final PrintMedia media; 
	
	public ConsoleMediaWithPosition(EnvelopeWithPosition envelope, PrintMedia media) {
		this.envelopeWithPosition = envelope;
		this.media = media;
	}

	@Override
	public void print() {
		media.print();
		System.out.println(" with position " + envelopeWithPosition.position());
	}

	@Override
	public Point position(Point position) {
		return envelopeWithPosition.position();
	}
	
	

}


