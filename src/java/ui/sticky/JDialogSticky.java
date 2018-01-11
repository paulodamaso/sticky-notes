package ui.sticky;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.note.Note;
import ui.PrintMedia;

/**
 * <p> Interface for a sticky {@link Note} with {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticky extends PrintMedia {

	public abstract JDialog jDialog();
//	public abstract JPopupMenu popUp();
	public abstract JTextArea txtArea();
	public abstract Application application();
//	public abstract JMenuItem colorMenuItem();
	public abstract JMenuItem saveItem();
	
	public abstract Envelope envelope();
	
	@Override
	public default void print() {
		if (!jDialog().isVisible()) jDialog().setVisible(true);
	}
	
	//saves sticky note info
	public default void save () {
		envelope().printDecorations(envelope());
	}

}
