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
	public Note note() {
		return origin.note();
	}

	@Override
	public Envelope persist(Envelope persistent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color color() {
		return this.color;
	}

}
