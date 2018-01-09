package main.envelope.color;

import java.awt.Color;

import main.envelope.Envelope;

public final class SimpleEnvelopeWithColor implements EnvelopeWithColor {

	private final Envelope envelope;
	private final Color color;
	
	public SimpleEnvelopeWithColor(Envelope origin, Color color) {
		this.envelope = origin;
		this.color = color;
	}

	@Override
	public int id() {
		return envelope.id();
	}

	@Override
	public String text() {
		return envelope.text();
	}

	@Override
	public void text(String text) {
		 envelope.text(text);
	}

	@Override
	public Color color() {
		return color;
	}

	@Override
	public SimpleEnvelopeWithColor color(Color color) {
		return new SimpleEnvelopeWithColor(envelope, color);
	}

	@Override
	public Envelope origin() {
		return envelope;
	}
}
