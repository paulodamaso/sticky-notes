package temp.ui;

import main.note.Note;

/**
 * <p> Media for printing a {@link Note}.
 * 
 * @author paulodamaso
 *
 */
public interface PrintMedia<T> {

		PrintMedia<T> with(String k, String v);
		T output();	
}
