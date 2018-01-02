package temp.envelope.jdialog.font;

import java.util.ArrayList;
import java.util.Collection;

import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.note.Note;
import temp.envelope.Envelope;
import temp.envelope.jdialog.JDialogEnvelope;
import temp.envelope.jdialog.JDialogEnvelopes;

public class JDialogEnvelopesWithFont implements JDialogEnvelopes , EnvelopesWithFont{
	
	private final JDialogEnvelopes jDialogEnvelopes;
	private final EnvelopesWithFont envelopesWithFont;
	
	public JDialogEnvelopesWithFont(JDialogEnvelopes jDialogEnvelopes, EnvelopesWithFont envelopesWithFont) {
		this.jDialogEnvelopes = jDialogEnvelopes;
		this.envelopesWithFont = envelopesWithFont;

	}

	@Override
	public Collection<JDialogEnvelope> envelopes() {
		Collection<JDialogEnvelope> it =  jDialogEnvelopes.envelopes();
		Collection<EnvelopeWithFont> itFont = envelopesWithFont.iterateInFont();
		
		Collection<JDialogEnvelope> toRemove = new ArrayList<JDialogEnvelope>();
		Collection<JDialogEnvelope> toAdd = new ArrayList<JDialogEnvelope>();
		
		for(JDialogEnvelope env : it) {
			for(EnvelopeWithFont envFont : itFont) {
				if(env.id() == envFont.id()) {
					toRemove.add(env);
					toAdd.add(new JDialogEnvelopeWithFont(env, envFont));
				}
			}
		}
		
		it.removeAll(toRemove);
		it.addAll(toAdd);

		return it;
	}

	@Override
	public Collection<Envelope> iterate() {
		return envelopesWithFont.iterate();
	}

	@Override
	public Collection<EnvelopeWithFont> iterateInFont() {
		return envelopesWithFont.iterateInFont();
	}

	@Override
	public JDialogEnvelope add(Note note) {
		return jDialogEnvelopes.add(note);
	}

}
