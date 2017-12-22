package main.ui.swing.sticker;

import main.persistence.Persistent;

public interface PersistentSticker<T> extends Sticker, Persistent<T> {

	public abstract T persist(T sticker) ;
}
