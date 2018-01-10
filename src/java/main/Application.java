package main;

import main.envelope.Envelope;
import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;
import ui.MediaFactory;

/**
 * <p> Application interface for actions.
 * 
 * @author paulodamaso
 *
 */
public interface Application {

	public Envelope add(Envelope envelope) ;
	
	public Envelopes envelopes() ;
	
	public Application start() throws Exception;
	
	public EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory();
	public EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory();
	public EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory();
	public EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory();
	
	public MediaFactory mediaFactory();
}
