package main.ui.swing.jdialog;

import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;

public class JDialogStickers implements Stickers {
	
	private final Stickers origin;

	public JDialogStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> ret = new ArrayList<Sticker>();
		for(Sticker stk : origin.iterate()) {
			ret.add(new SimpleDialogSticker(stk));
		}
		return ret;
	}

	@Override
	public Sticker add(String text) {
		return origin.add(text);
	}

}
