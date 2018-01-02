package temp.ui.jdialog;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import temp.ui.jdialog.position.derby.JDialogStickerWithPosition;
import temp.ui.jdialog.size.derby.JDialogStickerWithSize;

/**
 * <p> {@link ComponentListener} for events in {@link JDialogSticker}.
 * 
 * <p> The {@link JDialogSticker} is decorated with its new position / size, but it's not saved yet.
 *  
 * @author paulodamaso
 *
 */
public final class StickerComponentListener implements ComponentListener {

	private JDialogSticker sticker;
	
	public StickerComponentListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		this.sticker = new JDialogStickerWithSize(sticker, e.getComponent().getSize(), "resources/database/sticky-notes-db");
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		this.sticker = new JDialogStickerWithPosition(sticker, e.getComponent().getLocation(), "resources/database/sticky-notes-db");
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
