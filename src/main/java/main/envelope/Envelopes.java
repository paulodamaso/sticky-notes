package main.envelope;

import java.util.Collection;

/**
 * <p> Collection of {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface Envelopes {
	
	public Collection<Envelope> iterate();
	
	public Envelope add(String note);
}
