package main.envelope.sticky.color;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.color.derby.DerbyEnvelopeWithColor;
import main.envelope.sticky.StickyJDialogEnvelope;

public final class JDialogColorActionListener implements ActionListener {
	
	private final StickyJDialogEnvelope envelope; 

	public JDialogColorActionListener(StickyJDialogEnvelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//show a color selection dialog
		Color newColor = JColorChooser.showDialog(null,
                "Choose Color",
                envelope.textArea().getBackground()
                );
		
	
		EnvelopeWithColor envelopeColor = new DerbyEnvelopeWithColor(new SimpleEnvelopeWithColor(envelope, newColor), "resources/database/sticky-notes-db");
		envelopeColor.persist(new SimpleEnvelopeWithColor(envelope, newColor));
		new StickyJDialogEnvelopeWithColor(envelope, envelopeColor);
	}
}
