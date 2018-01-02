package main.envelope.sticky.font;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.envelope.sticky.SimpleStickyJDialogEnvelope;
import main.envelope.sticky.StickyJDialogEnvelopes;
import main.note.Note;

public final class StickyJDialogEnvelopesWithFont implements StickyJDialogEnvelopes, EnvelopesWithFont {
	
	private final StickyJDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithFont envelopesWithFont;

	public StickyJDialogEnvelopesWithFont(StickyJDialogEnvelopes jDialogEnvelopes, EnvelopesWithFont envelopesWithFont) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithFont = envelopesWithFont;
	}

	@Override
	public Collection<Envelope> envelopes() {
		Collection<Envelope> it =  jDialogEnvelopes.envelopes();
		Collection<EnvelopeWithFont> itFont = envelopesWithFont.iterateInFont();
		
		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
		
		for(Envelope env : it) {
			for(EnvelopeWithFont envFont : itFont) {
				if(env.id() == envFont.id()) {
					toRemove.add(env);
					toAdd.add(new StickyJDialogEnvelopeWithFont(env, envFont));
				}
			}
		}
		
		it.removeAll(toRemove);
		it.addAll(toAdd);

		return it;
	}

	@Override
	public Envelope envelope(Note note) {
		return jDialogEnvelopes.envelope(note);
	}

	@Override
	public Collection<EnvelopeWithFont> iterateInFont() {
		return envelopesWithFont.iterateInFont();
	}

}
