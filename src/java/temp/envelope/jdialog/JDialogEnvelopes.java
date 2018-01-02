package temp.envelope.jdialog;

import java.util.Collection;

import main.note.Note;

public interface JDialogEnvelopes {
	
	public Collection<JDialogEnvelope> envelopes();
	public JDialogEnvelope add(Note note);
	
}
