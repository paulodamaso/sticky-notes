package temp.ui.jdialog.size.derby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import temp.ui.jdialog.JDialogSticker;

public class StickerSizeActionListener implements ActionListener {

	private JDialogSticker sticker;

	public StickerSizeActionListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Saving by save menu " + sticker.id() + " if it has changed size");
		if (sticker.jdialog().getWidth() != 300 && sticker.jdialog().getHeight() != 150) {
			
			/*
			 * @todo #26 we shouldn't allow to re-decorate a JDialogStickerWithSize
			 */
			/*
			 * @todo #26 decorate persistence mechanism in StickerSizeActionListener
			 *  this decoration should be made with a interface of JDialogStickerWithSize, and not
			 *  a concrete class
			 */
			sticker = new JDialogStickerWithSize(sticker, sticker.jdialog().getSize(), "resources/database/sticky-notes-db");
			sticker.persist(sticker);
		}
	}
}