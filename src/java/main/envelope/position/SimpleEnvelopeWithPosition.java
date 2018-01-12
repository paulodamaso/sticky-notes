package main.envelope.position;

import java.awt.Point;

import main.envelope.Envelope;

/**
 * <p> Simple envelope with position support, with position information in memory.
 * 
 * @author paulodamaso
 *
 */
public class SimpleEnvelopeWithPosition implements EnvelopeWithPosition {

	private final Envelope origin;
	private final Point position;
			

	public SimpleEnvelopeWithPosition(Envelope origin, Point position) {
		this.origin = origin;
		this.position = position;
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public void text(String text) {
		origin.text(text);
	}

	@Override
	public Envelope origin() {
		return origin;
	}

	@Override
	public Point position() {
		return position;
	}

	@Override
	public EnvelopeWithPosition position(Point position) {
		return new SimpleEnvelopeWithPosition(origin, position);
	}
}
