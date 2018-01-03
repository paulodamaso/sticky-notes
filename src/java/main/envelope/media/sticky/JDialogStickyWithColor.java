package main.envelope.media.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.media.MediaWithColor;
import main.envelope.media.PrintMedia;

/**
 * <p> {@link JDialogSticky} with color information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithColor implements JDialogSticky , MediaWithColor{
	
	private final EnvelopeWithColor envelopeWithColor;
	private final JDialogSticky origin;
	private final PrintMedia media;

	public JDialogStickyWithColor(JDialogStickyWithColor jdiag, PrintMedia media) {
		this.envelopeWithColor = envelopeWithColor;
		this.origin = origin;
		this.media = media;
		
		this.txtArea().setBackground(this.envelopeWithColor.color());

	}

	@Override
	public JDialog jDialog() {
		return origin.jDialog();
	}

	@Override
	public Envelope envelope() {
		return origin.envelope();
	}

	@Override
	public JPopupMenu popUp() {
		return origin.popUp();
	}

	@Override
	public JTextArea txtArea() {
		return origin.txtArea();
	}

	@Override
	public void print() {
		media.print();
	}
}
