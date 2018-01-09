package main.envelope.size;

import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;

/**
 * <p> Decorated collection of {@link Envelope} which can retrieve size data when available.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopesWithSize extends Envelopes {
	
	/**
	 * <p> Retrieves all {@link Envelope} instances with size data from this collection.
	 * 
	 * @return
	 */
	public Collection<EnvelopeWithSize> iterateInSize();
	
	public Envelopes origin();

}
