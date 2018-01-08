package main.envelope.media.sticky;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.media.PrintMedia;

public final class StickyColorActionListener implements ActionListener {
	

	public final Envelope envelope;
	public final Envelopes envelopes;
	public final PrintMedia media;

	public StickyColorActionListener(Envelope envelope, Envelopes envelopes, PrintMedia media) {
		this.envelope = envelope;
		this.envelopes = envelopes;
		this.media = media;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//show a colorchooser
   		Color newColor = JColorChooser.showDialog(null,
                   "Choose Color",
                   new Color(251,247,174)
                   );
   		if (newColor != null) {
   			EnvelopeWithColor envC = new SimpleEnvelopeWithColor(envelope, newColor);
   			envelopes.add(envC);
   			new JDialogStickyWithColor(envC, media); 
   		}
   		
   		
	}

}
