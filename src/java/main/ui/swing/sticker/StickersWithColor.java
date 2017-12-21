package main.ui.swing.sticker;

import java.awt.Color;
import java.util.ArrayList;

public abstract class StickersWithColor implements Stickers {
	
	private final Stickers origin;

	public StickersWithColor(Stickers stickers) {
		origin = stickers;
	}

	//iterate over stickers, adding color to them
	@Override	
	public Iterable<Sticker> iterate() {
		ArrayList<Sticker> arr = new ArrayList<Sticker>();
		for (Sticker sticker : origin.iterate()) {
			//just to avoid two database queries
			Color color = color(sticker);

			if (color != null) 
				sticker = new StickerWithColor(sticker, color);

			arr.add(sticker);
		}
		return arr;
	}
	
	//return the color of a sticker
	public abstract Color color(Sticker sticker);

}
