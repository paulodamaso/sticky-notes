package main.ui.swing.jdialog.old;

import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.ui.PrintedSticker;

/**
 * <p> {@link Sticker} with text presentation using a {@link JTextArea}.
 * 
 * @author paulodamaso
 *
 */
public interface TextSticker extends PrintedSticker {

	public abstract JTextArea textArea();
}
