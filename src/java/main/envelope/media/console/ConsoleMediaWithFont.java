package main.envelope.media.console;

import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.MediaWithFont;
import main.envelope.media.PrintMedia;

/**
 * <p> Print {@link EnvelopeWithFont} to console.
 */
public final class ConsoleMediaWithFont implements MediaWithFont {
	
	private final EnvelopeWithFont envelopeWithFont;
	private final PrintMedia media;

	public ConsoleMediaWithFont(EnvelopeWithFont envelope, PrintMedia media) {
		this.envelopeWithFont = envelope;
		this.media = media;
	}

	@Override
	public void print() {
		media.print();
		System.out.println(" with font " + envelopeWithFont.font());
	}

}
