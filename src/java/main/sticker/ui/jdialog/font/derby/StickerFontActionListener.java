package main.sticker.ui.jdialog.font.derby;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.sticker.ui.jdialog.JDialogSticker;
import main.sticker.ui.jdialog.font.NwFontChooserS;

public final class StickerFontActionListener implements ActionListener {
	
	private JDialogSticker sticker;

	public StickerFontActionListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//show a fontchooser
		/*
		 * @todo #27 make my own FontChooser
		 */
		Font newFont = NwFontChooserS.showDialog(null, null, null);

		/*
		 * @todo #27 we shouldn't allow to re-decorate a JDialogStickerWithFont
		 */
		/*
		 * @todo #27 decorate persistence mechanism in StickerFontActionListener
		 *  this decoration should be made with a interface of JDialogStickerWithFnt, and not
		 *  a concrete class
		 */
		this.sticker = new JDialogStickerWithFont(sticker, newFont, "resources/database/sticky-notes-db");
	}

}
