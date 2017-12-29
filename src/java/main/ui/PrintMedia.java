package main.ui;

import main.sticker.Sticker;

/**
 * <p> Media for printing a {@link Sticker}.
 * 
 * @author paulodamaso
 *
 */
public interface PrintMedia<T> {

		PrintMedia<T> with(String k, String v);
		T output();	
}
