package ui.console;

import java.awt.Point;

import main.Messages;
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
		System.out.println(Messages.getString("ConsoleMediaWithPosition.printText") + envelopeWithPosition.position()); //$NON-NLS-1$
	}

	@Override
	public Point position(Point position) {
		return envelopeWithPosition.position();
	}
	
	

}


