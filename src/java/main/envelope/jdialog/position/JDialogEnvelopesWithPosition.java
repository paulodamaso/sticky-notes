package main.envelope.jdialog.position;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.jdialog.JDialogEnvelope;
import main.envelope.jdialog.JDialogEnvelopes;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;
import main.note.Note;

public class JDialogEnvelopesWithPosition implements JDialogEnvelopes, EnvelopesWithPosition {

	private final JDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithPosition envelopesWithPosition;
	
	public JDialogEnvelopesWithPosition(JDialogEnvelopes jDialogEnvelopes, EnvelopesWithPosition envelopesWithPosition) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithPosition = envelopesWithPosition;
	}
	
	@Override
	public Collection<Envelope> iterate() {
		return envelopesWithPosition.iterate();
	}

	@Override
	public Collection<EnvelopeWithPosition> iterateInPosition() {
		return envelopesWithPosition.iterateInPosition();
	}

	@Override
	public Collection<JDialogEnvelope> envelopes() {
		Collection<JDialogEnvelope> it =  jDialogEnvelopes.envelopes();
		Collection<EnvelopeWithPosition> itPosition = envelopesWithPosition.iterateInPosition();
		
		Collection<JDialogEnvelope> toRemove = new ArrayList<JDialogEnvelope>();
		Collection<JDialogEnvelope> toAdd = new ArrayList<JDialogEnvelope>();
		
		for(JDialogEnvelope env : it) {
			for(EnvelopeWithPosition envPosition : itPosition) {
				if(env.id() == envPosition.id()) {
					toRemove.add(env);
					toAdd.add(new JDialogEnvelopeWithPosition(env, envPosition));
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
