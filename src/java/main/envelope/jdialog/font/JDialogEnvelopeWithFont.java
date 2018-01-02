package main.envelope.jdialog.font;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.jdialog.JDialogEnvelope;
import main.note.Note;

public class JDialogEnvelopeWithFont implements JDialogEnvelope, EnvelopeWithFont {

	private final EnvelopeWithFont envelopeWithFont;
	private final JDialogEnvelope envelope;
	
	public JDialogEnvelopeWithFont(JDialogEnvelope envelope,  EnvelopeWithFont envelopeWithFont) {

		this.envelopeWithFont = envelopeWithFont;
		this.envelope = envelope;
		
        textArea().setFont(this.envelopeWithFont.font());
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
	public void print() {
		envelope.print();
	}

	@Override
	public Envelope persist(Envelope persistent) {
		return new JDialogEnvelopeWithFont(envelope, envelopeWithFont);
	}

	@Override
	public JDialog jDialog() {
		return envelope.jDialog();
	}

	@Override
	public JTextArea textArea() {
		return envelope.textArea();
	}

	@Override
	public JPopupMenu popUp() {
		return envelope.popUp();
	}

	@Override
	public Font font() {
		return envelopeWithFont.font();
	}

}
