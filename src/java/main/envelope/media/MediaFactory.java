package main.envelope.media;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;

/**
 * <p> Factory for creation of {@link PrintMedia}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaFactory {
	
	public SimpleMedia create(Envelope envelope);
	public MediaWithColor create(EnvelopeWithColor envelopeWithColor, PrintMedia media);
	public MediaWithFont create(EnvelopeWithFont envelopeWithFont, PrintMedia media);
	
}
