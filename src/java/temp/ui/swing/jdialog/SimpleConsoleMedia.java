package temp.ui.swing.jdialog;

import temp.ui.PrintMedia;
import temp.ui.PrintedSticker;

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
