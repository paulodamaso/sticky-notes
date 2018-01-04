package main.envelope.media.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.media.MediaWithSize;
import main.envelope.media.PrintMedia;
import main.envelope.size.EnvelopeWithSize;

/**
 * <p> {@link JDialogSticky} with color information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithSize implements JDialogSticky , MediaWithSize{
	
	private final EnvelopeWithSize envelopeWithSize;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithSize without type casting
	 */
	public JDialogStickyWithSize(EnvelopeWithSize envelopeWithSize, PrintMedia media) {
		this.envelopeWithSize = envelopeWithSize;
		this.media = (JDialogSticky)media;
		
		jDialog().setSize((this.envelopeWithSize.size()));

	}

	@Override
	public JDialog jDialog() {
		return media.jDialog();
	}

	@Override
	public Envelope envelope() {
		return media.envelope();
	}

	@Override
	public JPopupMenu popUp() {
		return media.popUp();
	}

	@Override
	public JTextArea txtArea() {
		return media.txtArea();
	}

	@Override
	public void print() {
		media.print();
	}
}
