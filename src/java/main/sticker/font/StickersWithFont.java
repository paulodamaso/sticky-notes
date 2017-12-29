package main.sticker.font;

import java.util.Collection;

import main.sticker.Stickers;

public interface StickersWithFont extends Stickers {

	public Collection<StickerWithFont> iterateInFont();
}
