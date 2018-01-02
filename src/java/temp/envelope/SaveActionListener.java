package temp.envelope;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.envelope.jdialog.JDialogEnvelope;
import main.envelope.persistence.DerbyEnvelope;
import main.note.persistence.derby.DerbyNote;

public final class SaveActionListener implements ActionListener {
	
	private final JDialogEnvelope envelope;

	public SaveActionListener(JDialogEnvelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Note note = new simpl
		envelope.jDialog()
		envelope.persist(envelope.);
	}

}
