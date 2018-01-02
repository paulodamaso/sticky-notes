package temp.ui;

import main.note.Note;

/**
 * <p> A {@link Note} which printed to a {@link PrintMedia}.
 * 
 * @author paulodamaso
 *
 */
public interface PrintedSticker extends Note {

	public PrintMedia printed(PrintMedia media);
}
