package temp.envelope.position;

import java.awt.Point;

import temp.envelope.Envelope;

/**
 * <p> A decorator to add position to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithPosition extends Envelope {

	public Point position();
	
}
