package main.envelope.font;

import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;


/**
 * <p> Decorated collection of {@link Envelope} which can retrieve font data when available.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopesWithFont extends Envelopes {

	/**
	 * <p> Retrieves all {@link Envelope} instances with font data from this collection.
	 * 
	 * @return
	 */
	public Collection<EnvelopeWithFont> iterateInFont();
	
	/**
	 * <p> Retrieves the {@link Envelopes} decorated by this object.
	 *   
	 * @return
	 */
	public Envelopes origin();
}
