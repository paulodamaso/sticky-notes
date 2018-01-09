package console.media;

import main.envelope.Envelope;
import main.envelope.media.MediaWithPosition;
import main.envelope.media.PrintMedia;
import main.envelope.position.EnvelopeWithPosition;

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
	public void print(Envelope envelope) {
		media.print(envelope);
		System.out.println(" with position " + envelopeWithPosition.position());
	}

}


