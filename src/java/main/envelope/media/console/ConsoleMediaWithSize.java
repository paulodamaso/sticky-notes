package main.envelope.media.console;

import main.envelope.media.MediaWithSize;
import main.envelope.media.PrintMedia;
import main.envelope.size.EnvelopeWithSize;

/**
 * <p> Print {@link EnvelopeWithSize} to console.
 */
public final class ConsoleMediaWithSize implements MediaWithSize {

	private final EnvelopeWithSize envelopeWithSize;
	private final PrintMedia media; 
	
	public ConsoleMediaWithSize(EnvelopeWithSize envelope, PrintMedia media) {
		this.envelopeWithSize = envelope;
		this.media = media;
	}

	@Override
	public void print() {
		media.print();
		System.out.println(" with size " + envelopeWithSize.size());
	}

}


