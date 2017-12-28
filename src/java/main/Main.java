package main;

import main.sticker.Stickers;
import main.sticker.color.derby.DerbyStickersWithColor;
import main.sticker.font.derby.DerbyStickersWithFont;
import main.sticker.persistence.derby.DerbyStickers;
import main.sticker.position.derby.DerbyStickersWithPosition;
import main.sticker.size.derby.DerbyStickersWithSize;
import main.sticker.ui.jdialog.JDialogStickers;
import main.sticker.ui.jdialog.SimpleJDialogStickers;
import main.sticker.ui.jdialog.color.derby.JDialogStickersWithColor;
import main.sticker.ui.jdialog.font.derby.JDialogStickersWithFont;
import main.sticker.ui.jdialog.position.derby.JDialogStickersWithPosition;
import main.sticker.ui.jdialog.size.derby.JDialogStickersWithSize;
import main.ui.swing.SystemTrayApplication;

public class Main {

	public static void main(String[] args) throws Exception{
	
		/*
		 * @todo #46 externalize sticker configuration in properties file and create them using factories
		 */
		
		//setting Stickers configuration (presentation only)
		//make printable stickers
		// + put color if it have color (from database "resources/database/sticky-notes-db")
		JDialogStickers jDialogstickers = 
				new JDialogStickersWithFont(
					new JDialogStickersWithSize(
						new JDialogStickersWithPosition(
								new JDialogStickersWithColor(
										new SimpleJDialogStickers(
												new DerbyStickers("resources/database/sticky-notes-db")
												),
										"resources/database/sticky-notes-db"),
								"resources/database/sticky-notes-db"),
					"resources/database/sticky-notes-db"),
				"resources/database/sticky-notes-db");
		
		//sticker data
		Stickers stickers =
				new DerbyStickersWithSize(
				new DerbyStickersWithPosition(
				new DerbyStickersWithFont(
				new DerbyStickersWithColor(
						new DerbyStickers("resources/database/sticky-notes-db"),
						"resources/database/sticky-notes-db"
						), 
				"resources/database/sticky-notes-db"
						), 
				"resources/database/sticky-notes-db"
						), 
				"resources/database/sticky-notes-db"
						);
		//enveloping sticker data in presentation 
				
		SystemTrayApplication app = new SystemTrayApplication(jDialogstickers);
				
		app.init();

	}

}
