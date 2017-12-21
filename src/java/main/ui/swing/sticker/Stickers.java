package main.ui.swing.sticker;

import main.ui.Printable;

public interface Stickers extends Printable {
	
	public Iterable<Sticker> iterate();
	
	public Sticker add(String task);

}
