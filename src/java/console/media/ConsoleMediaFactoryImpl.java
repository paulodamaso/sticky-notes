package console.media;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.MediaFactory;
import main.envelope.media.PrintMedia;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.size.EnvelopeWithSize;

/**
 * <p> {@link MediaFactory} for {@link PrintMedia} which prints to console.
 *  
 * @author paulodamaso
 *
 */
public class ConsoleMediaFactoryImpl implements MediaFactory {
	
	@Override
	public PrintMedia create(Envelope envelope) {
		PrintMedia ret = new SimpleConsoleMedia();
		if (envelope instanceof EnvelopeWithColor) ret = new ConsoleMediaWithColor((EnvelopeWithColor)envelope, ret);
		if (envelope instanceof EnvelopeWithFont) ret = new ConsoleMediaWithFont((EnvelopeWithFont)envelope, ret);
		if (envelope instanceof EnvelopeWithSize) ret = new ConsoleMediaWithSize((EnvelopeWithSize)envelope, ret);
		if (envelope instanceof EnvelopeWithPosition) ret = new ConsoleMediaWithPosition((EnvelopeWithPosition)envelope, ret);
		
		return ret;
	}

}
