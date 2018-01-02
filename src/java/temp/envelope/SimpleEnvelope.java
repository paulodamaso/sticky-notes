package temp.envelope;

import main.note.Note;

/**
 * <p> A simple {@link Envelope}.
 * 
 */
public final class SimpleEnvelope implements Envelope {

	private final Note note;
	
	public SimpleEnvelope(Note note) {
		this.note = note;
	}

	@Override
	public int id() {
		return note.id();
	}

	@Override
	public Note note() {
		return note;
	}

}
