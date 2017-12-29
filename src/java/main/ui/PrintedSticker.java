package main.ui;

import main.sticker.Sticker;

/**
 * <p> A {@link Sticker} which printed to a {@link PrintMedia}.
 * 
 * @author paulodamaso
 *
 */
public interface PrintedSticker extends Sticker {

	public PrintMedia printed(PrintMedia media);
}
