package ui.sticky;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.envelope.Envelope;

public final class SaveActionListener implements ActionListener {
	
	private final Envelope envelope;

	public SaveActionListener(Envelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		envelope.printDecorations(envelope);
	}

}
