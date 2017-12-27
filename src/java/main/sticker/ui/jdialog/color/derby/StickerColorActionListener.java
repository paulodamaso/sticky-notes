package main.sticker.ui.jdialog.color.derby;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import main.sticker.ui.jdialog.JDialogSticker;

public final class StickerColorActionListener implements ActionListener {
	
	private JDialogSticker sticker;

	public StickerColorActionListener(JDialogSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//show a colorchooser
		Color newColor = JColorChooser.showDialog(null,
                 "Choose Color",
                 new Color(251,247,174)
                 );
		/*
		 * @todo #24 we shouldn't allow to re-decorate a JDialogStickerWithColor
		 */
		/*
		 * @todo #24 decorate persistence mechanism in StickerColorActionListener
		 *  this decoration should be made with a interface of JDialogStickerWithColor, and not
		 *  a concrete class
		 */
		this.sticker = new JDialogStickerWithColor(sticker, newColor, "resources/database/donkey-tasks-db");
	}

}
