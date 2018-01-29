package main.envelope;

import main.note.Note;

/**
 * <p> A simple {@link Envelope} with basic behavior.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleEnvelope implements Envelope {

	private final Note origin;
	
	public SimpleEnvelope(Note origin) {
		this.origin = origin;
	}

	@Override
	public int id() {
		return this.origin.id();
	}

	@Override
	public String text() {
		return this.origin.text();
	}

	@Override
	public void text(String text) {
		origin.text(text);
	}

	/*
	 * @todo #88 does not seem right to just return null here
	 */
	@Override
	public Envelope origin() {
		return null;
	}

	@Override
	public void delete() {
		origin.delete();
	}
}
