package main.envelope.media;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.console.ConsoleMediaWithColor;
import main.envelope.media.console.ConsoleMediaWithFont;
import main.envelope.media.console.SimpleConsoleMedia;
import main.envelope.media.sticky.JDialogStickyWithColor;
import main.envelope.media.sticky.SimpleJDialogSticky;

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
		return new ConsoleMediaWithFont(envelopeWithFont, media);
	}

}
