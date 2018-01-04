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

	public abstract Dimension size();
}
