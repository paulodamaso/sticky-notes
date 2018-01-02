package temp.envelope.jdialog.color;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.color.EnvelopeWithColor;
import main.note.Note;
import temp.envelope.Envelope;
import temp.envelope.jdialog.JDialogEnvelope;

public class JDialogEnvelopeWithColor implements JDialogEnvelope, EnvelopeWithColor  {

	private final EnvelopeWithColor envelopeWithColor;
	private final JDialogEnvelope envelope;
	
	public JDialogEnvelopeWithColor(JDialogEnvelope envelope,  EnvelopeWithColor envelopeWithColor) {

		this.envelopeWithColor = envelopeWithColor;
		this.envelope = envelope;
		
        textArea().setBackground(this.envelopeWithColor.color());
	}

	@Override
	public Envelope persist(Envelope persistent) {
		return new JDialogEnvelopeWithColor(envelope, envelopeWithColor);
	}

	@Override
	public void print() {
		envelope.print();
	}

	@Override
	public JDialog jDialog() {
		return this.envelope.jDialog();
	}

	@Override
	public JTextArea textArea() {
		return this.envelope.textArea();
	}

	@Override
	public JPopupMenu popUp() {	
		return this.envelope.popUp();
	}

	@Override
	public int id() {
		return envelope.id();
	}

	@Override
	public Note note() {
		return envelope.note();
	}

	@Override
	public Color color() {
		return envelopeWithColor.color();
	}

}
