package main.envelope.position;

import java.awt.Point;

import main.envelope.Envelope;

/**
 * <p> A decorator to add position to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithPosition extends Envelope {

	public Point position();
	
}
