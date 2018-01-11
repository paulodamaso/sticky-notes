package ui;

import main.envelope.Envelope;

/**
 * <p> Factory for {@link PrintMedia} creation.
 * 
 * @author paulodamaso
 */
public interface MediaFactory {
	
	public PrintMedia create (Envelope envelope);
	
}
