package main.ui.swing.sticker.jdialog;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.ui.swing.sticker.Sticker;

/**
 * <p> {@link Sticker} decorated by a {@link JDialog}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogSticker extends Sticker {

	//public abstract JDialog jdialog();
	public abstract JTextArea txtDescription();
	public abstract JPopupMenu popup();

	
}
