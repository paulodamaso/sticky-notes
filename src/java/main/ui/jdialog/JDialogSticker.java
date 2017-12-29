package main.ui.jdialog;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.ui.Printable;
import main.ui.PrintedSticker;

/**
 * <p> {@link Sticker} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticker extends Printable, Sticker
{
	public abstract JDialog jdialog();
	public abstract JTextArea txtDescription();
	public abstract JPopupMenu popup();
	public abstract JMenuItem saveItem();

}
