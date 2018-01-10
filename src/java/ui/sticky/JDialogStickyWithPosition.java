package ui.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.position.EnvelopeWithPosition;
import ui.MediaWithPosition;
import ui.PrintMedia;

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
		
		this.media.jDialog().setTitle(this.envelopeWithPosition.getClass().toString());
		
		jDialog().setLocation(this.envelopeWithPosition.position());

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
