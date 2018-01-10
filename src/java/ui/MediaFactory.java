package ui;

import main.envelope.Envelope;

/**
 * <p> Factory for creation of {@link PrintMedia}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaFactory {
	
	public PrintMedia create(Envelope envelope);
	
}
