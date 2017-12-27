package main.sticker.ui.jdialog;

import java.util.ArrayList;
import java.util.Collection;

import main.sticker.PrintableSticker;
import main.sticker.Sticker;
import main.sticker.Stickers;

/**
 * <p> Simple implementation of a {@link JDialogStickers}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleJDialogStickers implements JDialogStickers {
	
	private final Stickers origin;

	public SimpleJDialogStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public void print() {
//		System.out.println("Printing SimpleJDialogStickers");
		for (PrintableSticker stk : iterate()) {
			stk.print();
		}
//		System.out.println("Ended Printing SimpleJDialogStickers");
	}

	@Override
	public Collection<JDialogSticker> iterate() {
//		System.out.println("Iterating in SimplejDialogStickers");
		ArrayList<JDialogSticker> stickers = new ArrayList<JDialogSticker>();
		for(Sticker stk : origin.iterate()) {
			stickers.add(new SimpleSticker(stk));
		}
//		System.out.println("Iterated in SimplejDialogStickers");
		return stickers;
	}

	@Override
	public JDialogSticker add(String text) {
		return new SimpleSticker(origin.add(text));
	}

	@Override
	public Stickers stickers() {
		return origin;
	}
}
