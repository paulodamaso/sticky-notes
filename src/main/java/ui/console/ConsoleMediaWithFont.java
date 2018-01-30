package ui.console;

import java.awt.Font;

import main.envelope.font.EnvelopeWithFont;
import main.i18n.Messages;
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
		System.out.println(Messages.getString("ConsoleMediaWithFont.printText") + envelopeWithFont.font()); //$NON-NLS-1$
	}

	@Override
	public Font font(Font font) {
		return envelopeWithFont.font();
	}

}
