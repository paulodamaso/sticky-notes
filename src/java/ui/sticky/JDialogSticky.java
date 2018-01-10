package ui.sticky;

import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;

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
//	public abstract Envelope envelope();
	public abstract JPopupMenu popUp();
	public abstract JTextArea txtArea();
	
	@Override
	public default void print(Envelope envelope) {
		if (!jDialog().isVisible()) jDialog().setVisible(true);
	}
	
	public default void clearListeners() {
		
		System.out.println("Clearing listeners ");
		for (ActionListener listener : txtArea().getLis) {
			System.out.println( listener);
//			txtArea().removeContainerListener(listener);
		}
	}

}
