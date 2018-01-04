package main.envelope.media.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.media.MediaWithPosition;
import main.envelope.media.PrintMedia;
import main.envelope.position.EnvelopeWithPosition;

/**
 * <p> {@link JDialogSticky} with position information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithPosition implements JDialogSticky , MediaWithPosition{
	
	private final EnvelopeWithPosition envelopeWithPosition;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithPosition without type casting
	 */
	public JDialogStickyWithPosition(EnvelopeWithPosition envelopeWithPosition, PrintMedia media) {
		this.envelopeWithPosition = envelopeWithPosition;
		this.media = (JDialogSticky)media;
		
		jDialog().setLocation(this.envelopeWithPosition.position());

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
