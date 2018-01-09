package main;

import main.envelope.Envelope;
import main.envelope.Envelopes;

/**
 * <p> Application interface for actions.
 * 
 * @author paulodamaso
 *
 */
public interface Application {

	public Envelope add(Envelope envelope) ;
	
	public Envelopes envelopes() ;
	
	public Application init() throws Exception;
}
