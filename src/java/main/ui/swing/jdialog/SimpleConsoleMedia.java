package main.ui.swing.jdialog;

import main.ui.PrintMedia;
import main.ui.PrintedSticker;

public class SimpleConsoleMedia implements PrintMedia {
	
	private PrintedSticker sticker	;

	public SimpleConsoleMedia(PrintedSticker sticker) {
		this.sticker = sticker;
	}

	@Override
	public void print() {
		System.out.println("Sticker with id: "+ sticker.id() + " and text " + sticker.text());
	}

}
