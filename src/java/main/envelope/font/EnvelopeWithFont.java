package main.envelope.font;

import java.awt.Font;

import main.envelope.Envelope;

/**
 * <p> A decorator to add font selection behavior to a {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface EnvelopeWithFont extends Envelope {

	public Font font();
}
