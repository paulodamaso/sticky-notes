package main.envelope.color;

import java.awt.Color;

import main.envelope.Envelope;


/**
 * <p> Decorator to add color to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithColor extends Envelope {
	
	public abstract Envelope origin();
	
	public abstract Color color();
	
	/**
	 * <p> Creates a new {@link EnvelopeWithColor} with new color information.
	 * 
	 * @param color
	 * @return
	 */
	public abstract EnvelopeWithColor color(Color color);

}
