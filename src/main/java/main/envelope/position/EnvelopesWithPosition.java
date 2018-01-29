package main.envelope.position;

import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;

/**
 * <p> Decorated collection of {@link Envelope} which can retrieve position data when available.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopesWithPosition extends Envelopes {
	
	/**
	 * <p> Retrieves all {@link Envelope} instances with position data from this collection.
	 * 
	 * @return
	 */
	public Collection<EnvelopeWithPosition> iterateInPosition();
	
	public Envelopes origin() ;

}
