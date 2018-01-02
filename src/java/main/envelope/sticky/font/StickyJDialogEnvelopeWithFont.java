package main.envelope.sticky.font;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.font.EnvelopeWithFont;
import main.envelope.sticky.StickyJDialogEnvelope;
import main.envelope.sticky.color.StickyJDialogEnvelopeWithColor;
import main.note.Note;

public class StickyJDialogEnvelopeWithFont implements EnvelopeWithFont, StickyJDialogEnvelope {

	private final EnvelopeWithFont envelopeWithFont;
	private final StickyJDialogEnvelope envelope;
	
	public StickyJDialogEnvelopeWithFont(StickyJDialogEnvelope envelope,  EnvelopeWithFont envelopeWithFont) {

		this.envelopeWithFont = envelopeWithFont;
		this.envelope = envelope;
		
        textArea().setFont(this.envelopeWithFont.font());
	}

	@Override
	public void print() {
		envelope.print();
	}

	@Override
	public int id() {
		return envelope.id();
	}

	@Override
	public String text() {
		return envelope.text();
	}

	@Override
	public StickyJDialogEnvelopeWithColor persist(Note persistent) {
		return new StickyJDialogEnvelopeWithFont(envelope, envelopeWithFont).persist(persistent);
	}

	@Override
	public Font font() {
		return envelopeWithFont.font();	
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
}
