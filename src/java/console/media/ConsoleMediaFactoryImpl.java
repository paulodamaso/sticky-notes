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
		PrintMedia ret = null; 
		if (envelope instanceof Envelope) ret = new SimpleConsoleMedia(); 
		if (envelope instanceof EnvelopeWithColor) ret = new ConsoleMediaWithColor(((EnvelopeWithColor)envelope), create(((EnvelopeWithColor)envelope).origin()));
		if (envelope instanceof EnvelopeWithFont) ret = new ConsoleMediaWithFont((EnvelopeWithFont)envelope, create(((EnvelopeWithFont)envelope).origin()));
		if (envelope instanceof EnvelopeWithSize) ret = new ConsoleMediaWithSize((EnvelopeWithSize)envelope, create(((EnvelopeWithSize)envelope).origin()));
		if (envelope instanceof EnvelopeWithPosition) ret = new ConsoleMediaWithPosition((EnvelopeWithPosition)envelope, create(((EnvelopeWithPosition)envelope).origin()));
		
		return ret;
	}

}
