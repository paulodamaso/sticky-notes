package main.envelope.media;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.size.EnvelopeWithSize;

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
	public MediaWithPosition create(EnvelopeWithPosition envelopeWithPosition, PrintMedia media);
	public MediaWithSize create(EnvelopeWithSize envelopeWithSize, PrintMedia media);
	
}
