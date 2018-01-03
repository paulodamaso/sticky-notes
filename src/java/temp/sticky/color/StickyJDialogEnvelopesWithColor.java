package temp.sticky.color;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.note.Note;
import temp.sticky.SimpleStickyJDialogEnvelope;
import temp.sticky.StickyJDialogEnvelopes;


public class StickyJDialogEnvelopesWithColor implements StickyJDialogEnvelopes, EnvelopesWithColor {

	private final StickyJDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithColor envelopesWithColor;
	
	public StickyJDialogEnvelopesWithColor(StickyJDialogEnvelopes jDialogEnvelopes, EnvelopesWithColor envelopesWithColor) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithColor = envelopesWithColor;
	}


	@Override
	public Collection<EnvelopeWithColor> iterateInColor() {
		return envelopesWithColor.iterateInColor();
	}
//
//	@Override
//	public Collection<Envelope> envelopes() {
//		Collection<Envelope> it =  jDialogEnvelopes.envelopes();
//		Collection<EnvelopeWithColor> itColor = envelopesWithColor.iterateInColor();
//		
//		Collection<Envelope> toRemove = new ArrayList<Envelope>();
//		Collection<Envelope> toAdd = new ArrayList<Envelope>();
//		
//		for(Envelope env : it) {
//			for(EnvelopeWithColor envColor : itColor) {
//				if(env.id() == envColor.id()) {
//					toRemove.add(env);
//					toAdd.add(new StickyJDialogEnvelopeWithColor(new SimpleStickyJDialogEnvelope(env), envColor));
//				}
//			}
//		}
//		
//		it.removeAll(toRemove);
//		it.addAll(toAdd);
//
//		return it;
//	}
}
