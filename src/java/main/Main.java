package main;

import main.sticker.persistence.derby.DerbyStickers;
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
		// + put color if it have color (from database "resources/database/donkey-tasks-db")
		JDialogStickers stickers = 
				new JDialogStickersWithFont(
					new JDialogStickersWithSize(
						new JDialogStickersWithPosition(
								new JDialogStickersWithColor(
										new SimpleJDialogStickers(
												new DerbyStickers("resources/database/donkey-tasks-db")
												),
										"resources/database/donkey-tasks-db"),
								"resources/database/donkey-tasks-db"),
					"resources/database/donkey-tasks-db"),
				"resources/database/donkey-tasks-db");
				
		SystemTrayApplication app = new SystemTrayApplication(stickers);
				
		app.init();

	}

}
