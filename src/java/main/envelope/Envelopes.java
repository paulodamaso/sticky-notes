package main.envelope;

import java.util.Collection;

import main.note.Note;

public interface Envelopes  {
	
	public Collection<Envelope> envelopes();
	public Envelope envelope(Note note);

}
