package temp.sticky;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.note.Note;
import main.note.Notes;

public final class SimpleStickyJDialogEnvelopes implements StickyJDialogEnvelopes {
	
	private final Notes origin;

	public SimpleStickyJDialogEnvelopes(Notes origin) {
		this.origin = origin;
	}

	public Envelope envelope(Note note) {
		return new SimpleStickyJDialogEnvelope(origin.add(note.text()));
	}
	
	@Override
	public Collection<StickyJDialogEnvelope> envelopes() {
		ArrayList<StickyJDialogEnvelope> ret = new ArrayList<StickyJDialogEnvelope>();
		for (Note note : origin.notes()) {
			ret.add(new SimpleStickyJDialogEnvelope(note));
		}
		return ret;
	}
}
