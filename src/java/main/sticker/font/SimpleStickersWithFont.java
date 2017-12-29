package main.sticker.font;

import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;

public final class SimpleStickersWithFont implements StickersWithFont {
	
	private final StickersWithFont origin;

	public SimpleStickersWithFont(StickersWithFont origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> it = origin.iterate();
		Collection<StickerWithFont> font = iterateInFont();

		Collection<Sticker> toRemove = new ArrayList<Sticker>();
		Collection<Sticker> toAdd = new ArrayList<Sticker>();
			
		for (StickerWithFont stkWc : font) {
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
	public Collection<StickerWithFont> iterateInFont() {
		return origin.iterateInFont();
	}

}
