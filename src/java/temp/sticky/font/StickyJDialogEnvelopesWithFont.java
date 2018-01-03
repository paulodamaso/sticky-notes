package temp.sticky.font;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.note.Note;
import temp.sticky.SimpleStickyJDialogEnvelope;
import temp.sticky.StickyJDialogEnvelope;
import temp.sticky.StickyJDialogEnvelopes;

public final class StickyJDialogEnvelopesWithFont implements StickyJDialogEnvelopes, EnvelopesWithFont {
	
	private final StickyJDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithFont envelopesWithFont;

	public StickyJDialogEnvelopesWithFont(StickyJDialogEnvelopes jDialogEnvelopes, EnvelopesWithFont envelopesWithFont) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithFont = envelopesWithFont;
	}

	@Override
	public Collection<Envelope> envelopes() {
		
	}

	@Override
	public Collection<EnvelopeWithFont> iterateInFont() {
		return envelopesWithFont.iterateInFont();
	}

	@Override
	public Collection<StickyJDialogEnvelope> iterate() {
		Collection<StickyJDialogEnvelope> it =  jDialogEnvelopes.iterate();
		Collection<EnvelopeWithFont> itFont = envelopesWithFont.iterateInFont();
		
		Collection<StickyJDialogEnvelope> toRemove = new ArrayList<StickyJDialogEnvelope>();
		Collection<StickyJDialogEnvelope> toAdd = new ArrayList<StickyJDialogEnvelope>();
		
		for(StickyJDialogEnvelope env : it) {
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


}
