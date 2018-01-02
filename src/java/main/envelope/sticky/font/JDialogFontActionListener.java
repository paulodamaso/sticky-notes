package main.envelope.sticky.font;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.SimpleEnvelopeWithFont;
import main.envelope.font.derby.DerbyEnvelopeWithFont;
import main.envelope.sticky.StickyJDialogEnvelope;

public final class JDialogFontActionListener implements ActionListener {
	
	private final StickyJDialogEnvelope envelope; 

	public JDialogFontActionListener(StickyJDialogEnvelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//show a font selection dialog
		Font newFont = NwFontChooserS.showDialog(null, "Choose font", envelope.textArea().getFont());
		
	
		EnvelopeWithFont envelopeFont = new DerbyEnvelopeWithFont(new SimpleEnvelopeWithFont(envelope, newFont), "resources/database/sticky-notes-db");
		envelopeFont.persist(new SimpleEnvelopeWithFont(envelope, newFont));
		new StickyJDialogEnvelopeWithFont(envelope, envelopeFont);

	}

}
