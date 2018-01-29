package ui;

import java.awt.Point;

import main.envelope.Envelope;

/**
 * <p> {@link PrintMedia} to deal with position information for {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaWithPosition extends PrintMedia {

	public Point position(Point position);
}
