package main.sticker.color;

import java.util.Collection;

import main.sticker.Stickers;

public interface StickersWithColor extends Stickers {

	public Collection<StickerWithColor> iterateInColor();
}
