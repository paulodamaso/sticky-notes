package main.envelope.media;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.sticky.JDialogStickyWithColor;
import main.envelope.media.sticky.JDialogStickyWithFont;
import main.envelope.media.sticky.JDialogStickyWithPosition;
import main.envelope.media.sticky.JDialogStickyWithSize;
import main.envelope.media.sticky.SimpleJDialogSticky;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.size.EnvelopeWithSize;

/**
 * <p> {@link MediaFactory} for {@link PrintMedia} which prints to console.
 *  
 * @author paulodamaso
 *
 */
public class MediaFactoryImpl implements MediaFactory {
	
	@Override
	public SimpleMedia create(Envelope envelope) {
		return new SimpleJDialogSticky(envelope);
//		return new SimpleConsoleMedia(envelope);
	}

	@Override
	public MediaWithColor create(EnvelopeWithColor envelopeWithColor, PrintMedia media) {
		return new JDialogStickyWithColor(envelopeWithColor, media);
//		return new ConsoleMediaWithColor(envelopeWithColor, media);
	}

	@Override
	public MediaWithFont create(EnvelopeWithFont envelopeWithFont, PrintMedia media) {
		return new JDialogStickyWithFont(envelopeWithFont, media);
//		return new ConsoleMediaWithFont(envelopeWithFont, media);
	}
	
	@Override
	public MediaWithPosition create(EnvelopeWithPosition envelopeWithPosition, PrintMedia media) {
		return new JDialogStickyWithPosition(envelopeWithPosition, media);
//		return new ConsoleMediaWithPosition(envelopeWithPosition, media);
	}

	@Override
	public MediaWithSize create(EnvelopeWithSize envelopeWithSize, PrintMedia media) {
		return new JDialogStickyWithSize(envelopeWithSize, media);
//		return new ConsoleMediaWithSize(envelopeWithSize, media);
	}

}
