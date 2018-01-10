package ui.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.size.EnvelopeWithSize;
import ui.MediaWithSize;
import ui.PrintMedia;

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
		
		this.media.jDialog().setTitle(this.envelopeWithSize.getClass().toString());
		
		jDialog().setSize((this.envelopeWithSize.size()));

	}

	@Override
	public JDialog jDialog() {
		return media.jDialog();
	}

	@Override
	public JPopupMenu popUp() {
		return media.popUp();
	}

	@Override
	public JTextArea txtArea() {
		return media.txtArea();
	}
}
