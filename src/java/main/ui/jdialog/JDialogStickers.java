package main.ui.jdialog;

import java.util.Collection;

import main.sticker.Stickers;
import main.ui.Printable;

/**
 * <p> {@link JDialogSticker} wrapper for a {@link Stickers}.
 * 
 * @author paulodamaso
 *
 */
public interface JDialogStickers extends Printable  {
	
	public JDialogSticker add(String text);
	
	public Collection<JDialogSticker> iterate();
	
	public Stickers stickers();

}
