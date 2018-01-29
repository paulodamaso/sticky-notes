package ui.console;

import java.awt.Color;

import main.Messages;
import main.envelope.color.EnvelopeWithColor;
import ui.MediaWithColor;
import ui.PrintMedia;

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
		System.out.println(Messages.getString("ConsoleMediaWithColor.printText") + envelopeWithColor.color()); //$NON-NLS-1$
	}

	@Override
	public Color color(Color color) {
		return envelopeWithColor.color();
	}
}


