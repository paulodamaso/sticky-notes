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
	

		/* @todo #6 implement a complete application, with taskbar icon and persisting postit tasks
		 * 
		 */
		
//		Tasks tsk = new DerbyTasks("resources/database/donkey-tasks-db");
		//new StickerTasks(tsk));
		
		//setting PersistentTasks configuration:
		//get simpletasks from derby database ("resources/database/donkey-tasks-db")
		//Tasks persistedTasks = new SimpleDerbyTasks("resources/database/donkey-tasks-db");
		
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
				
//				(//new StickerTasks(tsk));
//		new StickerTasks(
//				new MemoryTasks(
//						Arrays.asList(
//							new DerbyTaskWithColor(new DerbyTask("resources/database/donkey-tasks-db", 0), new Color (0,255,0)),
//							new DerbyTaskWithPosition(new DerbyTask("resources/database/donkey-tasks-db", 0), 
//									Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350 / 2,Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 50)
//							)
//						)
//				)
//		);
//				new MemoryTasks(
//				Arrays.asList(
//						new TaskWithColor(
//								new SimpleStickerTask(
//										new MemoryTask(1, "Tis' a task with red color")
//										) ,new Color(255, 0, 0)
//								),
//						new TaskWithFont(
//								new SimpleStickerTask(
//										new MemoryTask(2,  "A task with a beautiful font")
//										),								
//								new Font("Comic Sans MS", Font.BOLD, 24) 
//								),
//						new TaskWithPosition(
//								new SimpleStickerTask(
//										new MemoryTask(3,  "A task positioned in the middle of the screen")
//										), Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350 / 2,Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 50
//								),
//						new TaskWithSize(
//								new SimpleStickerTask(
//										new MemoryTask(4,  "A big, beautiful task filling the whole screen")
//										), Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height
//								),
//						new TaskWithFont (
//								new TaskWithColor(
//										new SimpleStickerTask(
//												new MemoryTask(5, "A beautiful green task with a radical font")
//												) ,new Color(0, 255, 0)
//										), new Font("Segoe", Font.ITALIC, 20)
//								)
//						)
//				)
//		)
//		);
		app.init();
		
		
		// TODO Auto-generated method stub

	}

}
