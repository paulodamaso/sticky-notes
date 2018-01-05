package main.envelope.size;

import java.awt.Dimension;

import main.envelope.Envelope;

/**
 * <p> A decorator to add position to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithSize extends Envelope {

	public abstract Envelope origin();
	
	public abstract EnvelopeWithSize size(Dimension size);
	
	public abstract Dimension size();
}
