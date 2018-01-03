package main.envelope.media.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.PrintMedia;

/**
 * <p> {@link JDialogSticky} with font information.
 * 
 * @author paulodamaso
 *
 */
public class JDialogStickyWithFont implements PrintMedia, JDialogSticky {

	private final EnvelopeWithFont envelopeWithFont;
	private final JDialogSticky origin;
	private final PrintMedia media;

	public JDialogStickyWithFont(EnvelopeWithFont envelopeWithFont, JDialogSticky origin, PrintMedia media) {
		this.envelopeWithFont = envelopeWithFont;
		this.origin = origin;
		this.media = media;
		
		this.txtArea().setFont(this.envelopeWithFont.font());

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
