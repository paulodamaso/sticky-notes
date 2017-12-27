package main.sticker.ui.jdialog.position.derby;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.sticker.ui.jdialog.JDialogSticker;

public class StickerPositionActionListener implements ActionListener {

	private JDialogSticker sticker;

	public StickerPositionActionListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Saving by save menu " + sticker.id() + " if it has changed position");
		if (sticker.jdialog().getX() != 0 && sticker.jdialog().getY() != 0) {
			
			/*
			 * @todo #26 we shouldn't allow to re-decorate a JDialogStickerWithPosition
			 */
			/*
			 * @todo #26 decorate persistence mechanism in StickerPositionActionListener
			 *  this decoration should be made with a interface of JDialogStickerWithPosition, and not
			 *  a concrete class
			 */
			sticker = new JDialogStickerWithPosition(sticker, new Point(sticker.jdialog().getX(), sticker.jdialog().getY()), "resources/database/donkey-tasks-db");
			sticker.persist(sticker);
		}
	}
}