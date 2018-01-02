package temp.ui.jdialog;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class SimpleStickerFocusListener implements FocusListener {
	
	private final JDialogSticker sticker;

	public SimpleStickerFocusListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// nothing happen when gaining focus
	}

	@Override
	public void focusLost(FocusEvent e) {
		//System.out.println("Saving by lostfocus " + sticker.id());
		sticker.persist(sticker);
	}
}
