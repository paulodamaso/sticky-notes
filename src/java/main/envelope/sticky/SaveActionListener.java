package main.envelope.sticky;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.note.SimpleNote;

public final class SaveActionListener implements ActionListener {

	private final StickyJDialogEnvelope envelope;
	
	public SaveActionListener(StickyJDialogEnvelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		envelope.persist(new SimpleNote(envelope.id(), envelope.textArea().getText()));
	}

}
