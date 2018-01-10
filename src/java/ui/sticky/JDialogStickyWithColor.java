package ui.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.color.EnvelopeWithColor;
import ui.MediaWithColor;
import ui.PrintMedia;

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
	 * @todo #22 create a JDialogStickyWithColor without type casting
	 */
	public JDialogStickyWithColor(EnvelopeWithColor envelopeWithColor, PrintMedia media) {
		this.envelopeWithColor = envelopeWithColor;
		this.media = (JDialogSticky)media;
		
		this.media.jDialog().setTitle(this.envelopeWithColor.getClass().toString());
		
		this.clearListeners();
		
		this.txtArea().setBackground(this.envelopeWithColor.color());

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
