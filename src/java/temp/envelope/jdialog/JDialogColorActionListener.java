package temp.envelope.jdialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.derby.DerbyEnvelopeWithColor;

public final class JDialogColorActionListener implements ActionListener {
	
	private JDialogEnvelope envelope;

	public JDialogColorActionListener(JDialogEnvelope envelope) {
		this.envelope = envelope;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//show a colorchooser
		Color newColor = JColorChooser.showDialog(null,
                 "Choose Color",
                 new Color(251,247,174)
                 );
		/*
		 * @todo #24 we shouldn't allow to re-decorate a JDialogStickerWithColor
		 */
		/*
		 * @todo #24 decorate persistence mechanism in StickerColorActionListener
		 *  this decoration should be made with a interface of JDialogStickerWithColor, and not
		 *  a concrete class
		 */
		EnvelopeWithColor env = new DerbyEnvelopeWithColor(envelope, newColor, "resources/database/sticky-notes-db");
		env.persist(env);
	}

}
