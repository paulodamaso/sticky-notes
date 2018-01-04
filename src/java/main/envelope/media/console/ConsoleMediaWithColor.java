package main.envelope.media.console;

import main.envelope.color.EnvelopeWithColor;
import main.envelope.media.MediaWithColor;
import main.envelope.media.PrintMedia;

/**
 * <p> Print {@link EnvelopeWithColor} to console.
 */
public final class ConsoleMediaWithColor implements MediaWithColor {

	private final EnvelopeWithColor envelopeWithColor;
	private final PrintMedia media; 
	
	public ConsoleMediaWithColor(EnvelopeWithColor envelope, PrintMedia media) {
		this.envelopeWithColor = envelope;
		this.media = media;
	}

	@Override
	public void print() {
		media.print();
		System.out.println(" with color " + envelopeWithColor.color());
	}

}


