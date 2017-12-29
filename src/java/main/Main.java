package main;

import java.util.ArrayList;

import main.sticker.Sticker;
import main.sticker.Stickers;
import main.sticker.color.StickersWithColor;
import main.sticker.color.derby.DerbyStickersWithColor;
import main.sticker.font.StickersWithFont;
import main.sticker.font.derby.DerbyStickersWithFont;
import main.sticker.persistence.derby.DerbyStickers;
import main.sticker.position.derby.DerbyStickersWithPosition;
import main.sticker.size.derby.DerbyStickersWithSize;
import main.ui.jdialog.JDialogStickers;
import main.ui.jdialog.SimpleJDialogStickers;
import main.ui.jdialog.color.derby.JDialogStickersWithColor;
import main.ui.jdialog.font.derby.JDialogStickersWithFont;
import main.ui.jdialog.position.derby.JDialogStickersWithPosition;
import main.ui.jdialog.size.derby.JDialogStickersWithSize;
import main.ui.swing.SystemTrayApplication;
import main.ui.swing.jdialog.SimpleDialogSticker;

public class Main {

	public static void main(String[] args) throws Exception{
	
		/*
		 * @todo #46 externalize sticker configuration in properties file and create them using factories
		 */
		
		//setting Stickers configuration (presentation only)
		//make printable stickers
		// + put color if it have color (from database "resources/database/sticky-notes-db")
//		JDialogStickers jDialogstickers = 
//				new JDialogStickersWithFont(
//					new JDialogStickersWithSize(
//						new JDialogStickersWithPosition(
//								new JDialogStickersWithColor(
//										new SimpleJDialogStickers(
//												new DerbyStickers("resources/database/sticky-notes-db")
//												),
//										"resources/database/sticky-notes-db"),
//								"resources/database/sticky-notes-db"),
//					"resources/database/sticky-notes-db"),
//				"resources/database/sticky-notes-db");
		
		//sticker data
//		Stickers stickers =
//				new DerbyStickersWithSize(
//				new DerbyStickersWithPosition(
//				new DerbyStickersWithFont(
//				new DerbyStickersWithColor(
//						new DerbyStickers("resources/database/sticky-notes-db"),
//						"resources/database/sticky-notes-db"
//						), 
//				"resources/database/sticky-notes-db"
//						), 
//				"resources/database/sticky-notes-db"
//						), 
//				"resources/database/sticky-notes-db"
//						);
		
		//stcker / persistence
		//all stickers
		Stickers basic = new DerbyStickers("resources/database/sticky-notes-db"); 
		Stickers stk = new main.ui.swing.jdialog.JDialogStickers(basic);
		
		//just stickers with color
		StickersWithColor stkWc = new main.ui.swing.jdialog.JDialogStickersWithColor(new DerbyStickersWithColor(
				stk,
				"resources/database/sticky-notes-db"));
		
		//mixed
		StickersWithFont stkWf = new main.ui.swing.jdialog.JDialogStickersWithFont(new DerbyStickersWithFont(
				stkWc, 
				"resources/database/sticky-notes-db"));
		

				
		SystemTrayApplication app = new SystemTrayApplication(stkWf);
				
		app.init();

	}

}
