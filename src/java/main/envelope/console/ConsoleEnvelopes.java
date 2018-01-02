package main.envelope.console;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.note.Note;
import main.note.Notes;

public final class ConsoleEnvelopes implements Envelopes {
	
	private final Notes origin;

	public ConsoleEnvelopes(Notes origin) {
		this.origin = origin;
	}

	public Envelope envelope(Note note) {
		return new ConsoleEnvelope(origin.add(note.text()));
	}

	@Override
	public Collection<Envelope> envelopes() {
		ArrayList<Envelope> ret = new ArrayList<Envelope>();
		for (Note note : origin.notes()) {
			ret.add(new ConsoleEnvelope(note));
		}
		return ret;
	}

}
