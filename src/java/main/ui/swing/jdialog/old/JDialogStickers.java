package main.ui.swing.jdialog.old;

import java.util.ArrayList;
import java.util.Collection;

import main.ui.swing.jdialog.JDialogMedia;

/**
 * <p> {@link JDialogMedia} container.
 * 
 * @author paulodamaso
 *
 */
public class JDialogStickers {

	private Collection<TextSticker> origin; 
	
	public JDialogStickers(Collection<TextSticker> stickers) {
		this.origin = stickers;
	}
	
	public Collection<JDialogMedia> wrap() {
		ArrayList<JDialogMedia> ret = new ArrayList<JDialogMedia>() ;
		for (TextSticker sticker : origin){
			ret.add(new JDialogMedia(sticker));
		}
		return ret;
	}

}
