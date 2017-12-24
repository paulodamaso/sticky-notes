package main.ui.swing.sticker.jdialog;

import java.util.ArrayList;

import main.ui.swing.sticker.Sticker;
import main.ui.swing.sticker.Stickers;

public final class JDialogStickers implements Stickers {
	
	private final Stickers origin;

	public JDialogStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public void print() {
		for (Sticker stk : iterate()) {
			stk.print();
		}
	}

	@Override
	public Iterable<Sticker> iterate() {
		ArrayList<Sticker> stickers = new ArrayList<Sticker>();
		for(Sticker stk : origin.iterate()) {
			stickers.add(new SimpleSticker(stk));
		}
		return stickers;
	}

	@Override
	public Sticker add(String sticker) {
		return origin.add(sticker);
	}

}
