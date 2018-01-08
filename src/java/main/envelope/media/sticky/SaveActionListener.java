package main.envelope.media.sticky;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.envelope.Envelope;
import main.envelope.Envelopes;

public final class SaveActionListener implements ActionListener {

	private final Envelope envelope;
	private final Envelopes envelopes;
	
	public SaveActionListener(Envelopes envelopes, Envelope envelope) {
		this.envelopes = envelopes;
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.envelopes.add(envelope);
	}

}
