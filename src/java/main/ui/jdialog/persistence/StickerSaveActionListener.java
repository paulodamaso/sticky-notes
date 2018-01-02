package main.ui.jdialog.persistence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.note.Note;

public class StickerSaveActionListener implements ActionListener {
	
	private Note sticker;

	public StickerSaveActionListener(Note sticker) {
		this.sticker = sticker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Saving by save menu " + sticker.id());
		sticker.persist(sticker);
	}

}
