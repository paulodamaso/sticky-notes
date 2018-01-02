package main.envelope.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;

public interface StickyJDialogEnvelope extends Envelope {

	public JDialog jDialog();
	public JTextArea textArea();
	public JPopupMenu popUp();

}
