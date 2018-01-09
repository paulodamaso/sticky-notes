package main.envelope.size;

import java.awt.Dimension;

import main.envelope.Envelope;

public class SimpleEnvelopeWithSize implements EnvelopeWithSize {

	private final Envelope origin;
	private final Dimension size;
			

	public SimpleEnvelopeWithSize(Envelope origin, Dimension size) {
		this.origin = origin;
		this.size = size;
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
	public Dimension size() {
		return size;
	}

	@Override
	public EnvelopeWithSize size(Dimension size) {
		return new SimpleEnvelopeWithSize(origin, size);
	}
}
