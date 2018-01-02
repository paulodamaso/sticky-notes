package main.envelope.color;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.note.Note;


public final class SimpleEnvelopesWithColor implements EnvelopesWithColor {
	
	private final EnvelopesWithColor origin;

	public SimpleEnvelopesWithColor(EnvelopesWithColor origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Envelope> envelopes() {
		Collection<Envelope> it = origin.envelopes();
		Collection<EnvelopeWithColor> color = iterateInColor();

		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
			
		for (EnvelopeWithColor stkWc : color) {
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
	public Collection<EnvelopeWithColor> iterateInColor() {
		return origin.iterateInColor();
	}

	@Override
	public Envelope envelope(Note note) {
		return origin.envelope(note);
	}
}
