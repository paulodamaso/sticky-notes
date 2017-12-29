package main.ui.jdialog;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.sticker.PrintableSticker;
import main.sticker.Sticker;

/**
 * <p> {@link Sticker} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticker extends PrintableSticker
{
	public abstract JDialog jdialog();
	public abstract JTextArea txtDescription();
	public abstract JPopupMenu popup();
	public abstract JMenuItem saveItem();

}
