package main.envelope.color;

import java.awt.Color;

import main.envelope.Envelope;

/**
 * <p> Decorator to add color to a {@link Envelope}.
 * @author paulodamaso
 *
 */
public interface EnvelopeWithColor extends Envelope{
	
	public abstract Color color();

}
