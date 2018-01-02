package temp.envelope.size;

import java.awt.Dimension;

import main.note.Envelope;

/**
 * <p> A decorator to add size information to a {@link Envelope}.
 * @author paulodamaso
 *
 */
public interface EnvelopeWithSize extends Envelope {

	public abstract Dimension size();
}
