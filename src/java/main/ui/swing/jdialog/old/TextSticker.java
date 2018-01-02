package main.ui.swing.jdialog.old;

import javax.swing.JTextArea;

import main.note.Note;
import main.ui.PrintedSticker;

/**
 * <p> {@link Note} with text presentation using a {@link JTextArea}.
 * 
 * @author paulodamaso
 *
 */
public interface TextSticker extends PrintedSticker {

	public abstract JTextArea textArea();
}
