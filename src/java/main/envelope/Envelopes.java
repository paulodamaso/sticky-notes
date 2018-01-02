package main.envelope;

import java.util.Collection;

import main.note.Note;

public interface Envelopes {

	public Collection<Envelope> iterate();
	public Envelope add(Note note);
}
