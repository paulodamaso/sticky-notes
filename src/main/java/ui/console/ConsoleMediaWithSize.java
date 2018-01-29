package ui.console;

import java.awt.Dimension;

import main.Messages;
import main.envelope.size.EnvelopeWithSize;
import ui.MediaWithSize;
import ui.PrintMedia;

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
		System.out.println(Messages.getString("ConsoleMediaWithSize.printText") + envelopeWithSize.size()); //$NON-NLS-1$
	}

	@Override
	public Dimension size(Dimension size) {
		return envelopeWithSize.size();
	}

}


