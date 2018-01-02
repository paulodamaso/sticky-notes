package main.envelope.color;

import java.awt.Color;

import main.envelope.Envelope;
import main.note.Note;


public class SimpleEnvelopeWithColor implements EnvelopeWithColor {
	
	private final Envelope origin;
	private final Color color;
	
	public SimpleEnvelopeWithColor(Envelope origin, Color color) {
		this.origin = origin;
		this.color = color;
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public void print() {
		origin.print();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public EnvelopeWithColor persist(Note persistent) {
		origin.persist(persistent);
		return new SimpleEnvelopeWithColor(origin, this.color);
	}

	@Override
	public Color color() {
		return this.color;
	}
}
