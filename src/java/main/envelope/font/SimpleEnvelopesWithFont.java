package main.envelope.font;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.note.Note;

public final class SimpleEnvelopesWithFont implements EnvelopesWithFont {
	
	private final EnvelopesWithFont origin;

	public SimpleEnvelopesWithFont(EnvelopesWithFont origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Envelope> envelopes() {
		Collection<Envelope> it = origin.envelopes();
		Collection<EnvelopeWithFont> font = iterateInFont();

		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
			
		for (EnvelopeWithFont stkWc : font) {
			for (Envelope stk : it) {
				if (stkWc.id() == stk.id()) {
					toRemove.add(stk);
					toAdd.add(stkWc);
				}
			}			
		}
		it.removeAll(toRemove);
		it.addAll(toAdd);
		
		return it;
	}


	@Override
	public Collection<EnvelopeWithFont> iterateInFont() {
		return origin.iterateInFont();
	}

	@Override
	public Envelope envelope(Note note) {
		return origin.envelope(note);
	}

}
