package main.envelope.jdialog;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.note.Note;

public class SimpleJDialogEnvelopes implements JDialogEnvelopes {
	
	private final Envelopes origin;
	
	public SimpleJDialogEnvelopes(Envelopes origin) {
		this.origin = origin;
	}

	@Override
	public Collection<JDialogEnvelope> envelopes() {
		ArrayList<JDialogEnvelope> it = new ArrayList<JDialogEnvelope>();
		for (Envelope env: origin.iterate()) {
			it.add(new SimpleJDialogEnvelope(env.note(), env.id()));
		}
		return it;
	}

	@Override
	public JDialogEnvelope add(Note note) {
		Envelope env = origin.add(note); 
		return new SimpleJDialogEnvelope(env.note(), env.id());
	}

}
