package main.ui.swing.jdialog.old;

import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;

public class TextStickers implements Stickers {
	
	private final Stickers origin;

	public TextStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> ret = new ArrayList<Sticker>();
		for(Sticker stk : origin.iterate()) {
			ret.add(new SimpleTextSticker(stk));
		}
		return ret;
	}

	@Override
	public Sticker add(String text) {
		return origin.add(text);
	}

}
