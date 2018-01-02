package temp.envelope.jdialog;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import temp.envelope.Envelope;

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
