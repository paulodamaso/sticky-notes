package main.sticker.ui.jdialog.persistence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.sticker.Sticker;

public class StickerSaveActionListener implements ActionListener {
	
	private Sticker sticker;

	public StickerSaveActionListener(Sticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Saving by save menu " + sticker.id());
		sticker.persist(sticker);
	}

}
