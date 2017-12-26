package main.sticker.ui.jdialog;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class StickerFocusListener implements FocusListener {
	
	private final JDialogSticker sticker;

	public StickerFocusListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// nothing happen when gaining focus
	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("Saving by lostfocus " + sticker.id());
		sticker.persist(sticker);
	}
}
