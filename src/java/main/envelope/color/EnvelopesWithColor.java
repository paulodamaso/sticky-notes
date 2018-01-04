package main.envelope.color;

import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;

/**
 * <p> Decorated collection of {@link Envelope} which can retrieve color data when available.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopesWithColor<T extends EnvelopeWithColor> extends Envelopes<T> {

	/**
	 * <p> Retrieves all {@link Envelope} instances with color data from this collection.
	 * 
	 * @return
	 */
	public Collection<T> iterateInColor();
}
