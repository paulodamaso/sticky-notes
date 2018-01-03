package main.envelope;

import main.envelope.media.MediaFactoryImpl;
import main.envelope.media.PrintMedia;
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
	public Note persist(Note persistent) {
		return this.origin.persist(persistent);	
	}

	@Override
	public PrintMedia media() {
		return new MediaFactoryImpl().create(this);
	}

}
