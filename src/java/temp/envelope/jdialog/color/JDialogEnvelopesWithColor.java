package temp.envelope.jdialog.color;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.note.Note;
import temp.envelope.jdialog.JDialogEnvelope;
import temp.sticky.StickyJDialogEnvelope;
import temp.sticky.StickyJDialogEnvelopes;




public class JDialogEnvelopesWithColor implements StickyJDialogEnvelopes, EnvelopesWithColor {
	
	private final StickyJDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithColor envelopesWithColor;
	
	public JDialogEnvelopesWithColor(StickyJDialogEnvelopes jDialogEnvelopes, EnvelopesWithColor envelopesWithColor) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithColor = envelopesWithColor;

	}


	@Override
	public Collection<EnvelopeWithColor> iterateInColor() {
		return envelopesWithColor.iterateInColor();
	}


	@Override
	public Envelope envelope(Note note) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Envelope> envelopes() {
		Collection<Envelope> it =  jDialogEnvelopes.envelopes();
		Collection<EnvelopeWithColor> itColor = envelopesWithColor.iterateInColor();
		
		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
		
		for(Envelope env : it) {
			for(EnvelopeWithColor envColor : itColor) {
				if(env.id() == envColor.id()) {
					toRemove.add(env);
					toAdd.add(new JDialogEnvelopeWithColor(env, envColor));
				}
			}
		}
		
		it.removeAll(toRemove);
		it.addAll(toAdd);

		return it;
	}

//	@Override
//	public JDialogEnvelope add(Note note) {
//		return jDialogEnvelopes.add(note);
//	}
	
	

}
