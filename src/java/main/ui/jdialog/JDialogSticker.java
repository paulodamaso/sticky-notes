package main.ui.jdialog;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.note.Note;
import main.ui.Printable;
import main.ui.PrintedSticker;

/**
 * <p> {@link Note} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticker extends Printable, Note
{
	public abstract JDialog jdialog();
	public abstract JTextArea txtDescription();
	public abstract JPopupMenu popup();
	public abstract JMenuItem saveItem();

}
