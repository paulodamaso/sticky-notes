package main.envelope.console;

import main.envelope.Envelope;
import main.note.Note;

/**
 * <p> A console envelope for printing a {@link Note}.
 * 
 * @author paulodamaso
 *
 */
public final class ConsoleEnvelope implements Envelope {

	private final Note note;
	
	public ConsoleEnvelope(Note note) {
		this.note = note;
	}

	@Override
	public int id() {
		return note.id();
	}

	@Override
	public String text() {
		return note.text();
	}

	@Override
	public void print() {
		System.out.println(note.id() + " - " + note.text());
	}

	@Override
	public Note persist(Note persistent) {
		return note.persist(persistent);
	}

}
