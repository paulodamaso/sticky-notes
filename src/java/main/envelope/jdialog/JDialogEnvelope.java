package main.envelope.jdialog;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;

/**
 * {@link JDialog} envelope.
 * @author paulodamaso
 *
 */
public interface JDialogEnvelope extends Envelope {
	
	public JDialog jDialog();
	public JTextArea textArea();
	public JPopupMenu popUp();
	public void print();

}
