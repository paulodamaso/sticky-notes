package ui.console;

import java.awt.Font;

import main.envelope.font.EnvelopeWithFont;
import ui.MediaWithFont;
import ui.PrintMedia;

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

	@Override
	public Font font(Font font) {
		return envelopeWithFont.font();
	}

}
