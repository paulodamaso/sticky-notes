package main.envelope.font;

import java.awt.Font;

import main.envelope.Envelope;

/**
 * <p> A decorator to add font information to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithFont extends Envelope {

	public abstract Envelope origin();
	
	public abstract Font font();
	
	public abstract EnvelopeWithFont font(Font font);
}
