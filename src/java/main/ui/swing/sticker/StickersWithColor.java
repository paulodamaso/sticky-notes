package main.ui.swing.sticker;

import java.awt.Color;
import java.util.ArrayList;

public abstract class StickersWithColor implements Stickers {
	
	private final Stickers origin;

	public StickersWithColor(Stickers stickers) {
		origin = stickers;
	}

	//iterate over stickers, adding color to them

	public abstract Iterable<Sticker> iterate();

	
	//return the color of a sticker
	public abstract Color color(Sticker sticker);	

}
