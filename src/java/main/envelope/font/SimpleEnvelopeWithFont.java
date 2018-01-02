package main.envelope.font;

import java.awt.Font;

import main.envelope.Envelope;
import main.note.Note;

public final class SimpleEnvelopeWithFont implements EnvelopeWithFont {
	
	private final Envelope envelope;
	private final Font font;
	

	public SimpleEnvelopeWithFont(Envelope envelope, Font font) {
		this.envelope = envelope;
		this.font = font;
	}

	@Override
	public void print() {
		envelope.print();
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
	public Note persist(Note persistent) {
		envelope.persist(persistent);
		return new SimpleEnvelopeWithFont(envelope, this.font);
	}

	@Override
	public Font font() {
		return this.font;
	}

}
