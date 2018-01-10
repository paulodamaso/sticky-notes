package ui.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.font.EnvelopeWithFont;
import ui.MediaWithFont;
import ui.PrintMedia;

/**
 * <p> {@link JDialogSticky} with font information.
 * 
 * @author paulodamaso
 *
 */
public class JDialogStickyWithFont implements JDialogSticky, MediaWithFont {

	private final EnvelopeWithFont envelopeWithFont;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithFont without type casting
	 */
	public JDialogStickyWithFont(EnvelopeWithFont envelopeWithFont, PrintMedia media) {
		this.envelopeWithFont = envelopeWithFont;
		this.media = (JDialogSticky)media;
		
		this.media.jDialog().setTitle(this.envelopeWithFont.getClass().toString());
		
		this.txtArea().setFont((this.envelopeWithFont.font()));

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
