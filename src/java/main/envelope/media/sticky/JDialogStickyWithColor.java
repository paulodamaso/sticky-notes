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
	private final JDialogSticky media;

	/*
	 * @ todo #22 make this without typecasting
	 */
	public JDialogStickyWithColor(EnvelopeWithColor envelopeWithColor, PrintMedia media) {
		this.envelopeWithColor = envelopeWithColor;
		this.media = (JDialogSticky)media;
		
		this.txtArea().setBackground(this.envelopeWithColor.color());

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
