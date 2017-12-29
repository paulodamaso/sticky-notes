package main.ui.swing.jdialog;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import main.sticker.Sticker;

public interface JDialogSticker extends Sticker {

	public abstract JDialog jdialog();
	public abstract JTextArea txtText();
}
