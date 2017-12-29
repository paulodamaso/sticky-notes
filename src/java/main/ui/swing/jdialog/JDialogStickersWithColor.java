package main.ui.swing.jdialog;

import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;
import main.sticker.color.StickersWithColor;

public final class JDialogStickersWithColor implements StickersWithColor {
	
	private final StickersWithColor origin;

	public JDialogStickersWithColor(StickersWithColor origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> it = origin.iterate();
		Collection<StickerWithColor> color = iterateInColor();

		Collection<Sticker> toRemove = new ArrayList<Sticker>();
		Collection<Sticker> toAdd = new ArrayList<Sticker>();
			
		for (StickerWithColor stkWc : color) {
			for (Sticker stk : it) {
				if (stkWc.id() == stk.id()) {
					toRemove.add(stk);
					toAdd.add(stkWc);
				}
			}			
		}
		it.removeAll(toRemove);
		it.addAll(toAdd);
		
		return it;
	}

	@Override
	public Sticker add(String text) {
		return origin.add(text);
	}

	@Override
	public Collection<StickerWithColor> iterateInColor() {
		return origin.iterateInColor();
	}

}
