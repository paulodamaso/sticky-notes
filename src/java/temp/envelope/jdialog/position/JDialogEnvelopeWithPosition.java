package temp.envelope.jdialog.position;

import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.position.EnvelopeWithPosition;
import main.note.Note;
import temp.envelope.Envelope;
import temp.envelope.jdialog.JDialogEnvelope;

public class JDialogEnvelopeWithPosition implements JDialogEnvelope, EnvelopeWithPosition {
	
	private final JDialogEnvelope jDialogEnvelope;
	private final EnvelopeWithPosition envelopeWithPosition;
	
	public JDialogEnvelopeWithPosition(JDialogEnvelope jDialogEnvelope, EnvelopeWithPosition envelopeWithPosition) {
		this.jDialogEnvelope = jDialogEnvelope;
		this.envelopeWithPosition = envelopeWithPosition;
		
		jDialog().setLocation(envelopeWithPosition.position());
	}

	@Override
	public int id() {
		return jDialogEnvelope.id();
	}

	@Override
	public Note note() {
		return jDialogEnvelope.note();
	}

	@Override
	public void print() {
		jDialogEnvelope.print();
	}

	@Override
	public Envelope persist(Envelope persistent) {
		return new JDialogEnvelopeWithPosition(jDialogEnvelope, envelopeWithPosition);
	}

	@Override
	public Point position() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JDialog jDialog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTextArea textArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPopupMenu popUp() {
		// TODO Auto-generated method stub
		return null;
	}

}
