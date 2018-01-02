package main.envelope.jdialog.color;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.envelope.jdialog.JDialogEnvelope;
import main.envelope.jdialog.JDialogEnvelopes;
import main.note.Note;


public class JDialogEnvelopesWithColor implements JDialogEnvelopes, EnvelopesWithColor {
	
	private final JDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithColor envelopesWithColor;
	
	public JDialogEnvelopesWithColor(JDialogEnvelopes jDialogEnvelopes, EnvelopesWithColor envelopesWithColor) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithColor = envelopesWithColor;

	}

	@Override
	public Collection<Envelope> iterate() {
		return envelopesWithColor.iterate();
	}

	@Override
	public Collection<EnvelopeWithColor> iterateInColor() {
		return envelopesWithColor.iterateInColor();
	}

	@Override
	public Collection<JDialogEnvelope> envelopes() {
		Collection<JDialogEnvelope> it =  jDialogEnvelopes.envelopes();
		Collection<EnvelopeWithColor> itColor = envelopesWithColor.iterateInColor();
		
		Collection<JDialogEnvelope> toRemove = new ArrayList<JDialogEnvelope>();
		Collection<JDialogEnvelope> toAdd = new ArrayList<JDialogEnvelope>();
		
		for(JDialogEnvelope env : it) {
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

	@Override
	public JDialogEnvelope add(Note note) {
		return jDialogEnvelopes.add(note);
	}
	
	

}
