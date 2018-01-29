package ui.sticky;

import javax.swing.JDialog;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.size.EnvelopeWithSize;
import ui.MediaFactory;
import ui.PrintMedia;

/**
 * <p> {@link MediaFactory} for creating {@link JDialog} based sticky-notes.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyMediaFactory implements MediaFactory {
	
	private final Application application;
	
	public JDialogStickyMediaFactory(Application application) {
		this.application = application;
	}
	
	@Override
	public PrintMedia create(Envelope envelope) {
//		System.out.println("create media for " + envelope);
		JDialogSticky ret = null; 
		if (envelope instanceof Envelope) ret = new JDialogSimpleSticky(envelope, application); 
		if (envelope instanceof EnvelopeWithColor) ret = new JDialogStickyWithColor(((EnvelopeWithColor)envelope), create(((EnvelopeWithColor)envelope).origin()));
		if (envelope instanceof EnvelopeWithFont) ret = new JDialogStickyWithFont((EnvelopeWithFont)envelope, create(((EnvelopeWithFont)envelope).origin()));
		if (envelope instanceof EnvelopeWithSize) ret = new JDialogStickyWithSize((EnvelopeWithSize)envelope, create(((EnvelopeWithSize)envelope).origin()));
		if (envelope instanceof EnvelopeWithPosition) ret = new JDialogStickyWithPosition((EnvelopeWithPosition)envelope, create(((EnvelopeWithPosition)envelope).origin()));
		return ret;
	}


}
