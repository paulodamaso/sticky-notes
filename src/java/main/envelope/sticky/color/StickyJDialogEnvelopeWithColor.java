package main.envelope.sticky.color;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.color.EnvelopeWithColor;
import main.envelope.sticky.StickyJDialogEnvelope;
import main.note.Note;



public class StickyJDialogEnvelopeWithColor implements StickyJDialogEnvelope, EnvelopeWithColor {
	
	private final EnvelopeWithColor envelopeWithColor;
	private final StickyJDialogEnvelope envelope;
	
	public StickyJDialogEnvelopeWithColor(StickyJDialogEnvelope envelope,  EnvelopeWithColor envelopeWithColor) {

		this.envelopeWithColor = envelopeWithColor;
		this.envelope = envelope;
		
        textArea().setBackground(this.envelopeWithColor.color());
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
		envelope.persist(persistent);
		envelopeWithColor.persist(persistent);
		return new StickyJDialogEnvelopeWithColor(envelope, envelopeWithColor);
	}

	@Override
	public Color color() {
		return envelopeWithColor.color();	
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
