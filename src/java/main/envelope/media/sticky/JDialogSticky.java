package main.envelope.media.sticky;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.Envelope;
import main.envelope.media.PrintMedia;
import main.note.Note;

/**
 * <p> Interface for a sticky {@link Note} with {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticky extends PrintMedia {

	public abstract JDialog jDialog();
	public abstract Envelope envelope();
	public abstract JPopupMenu popUp();
	public abstract JTextArea txtArea();
	
}
