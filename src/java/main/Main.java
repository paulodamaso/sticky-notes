package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Date;

import main.persistence.PersistentTask;
import main.persistence.PersistentTasks;
import main.persistence.derby.DerbyTask;
import main.persistence.derby.DerbyTaskWithColor;
import main.persistence.derby.DerbyTaskWithPosition;
import main.persistence.derby.DerbyTasksWithColor;
import main.persistence.derby.SimpleDerbyTasks;
import main.persistence.memory.MemoryTask;
import main.persistence.memory.MemoryTasks;
import main.ui.PrintableTask;
import main.ui.PrintableTasks;
import main.ui.swing.SimpleStickerTask;
import main.ui.swing.SimpleStickerTasks;
import main.ui.swing.StickerTasks;
import main.ui.swing.SystemTrayApplication;
import main.ui.swing.StickerTaskWithColor;
import main.ui.swing.TaskWithFont;
import main.ui.swing.TaskWithPosition;
import main.ui.swing.TaskWithSize;

public class Main {

	public static void main(String[] args) throws Exception{
	

		/* @todo #6 implement a complete application, with taskbar icon and persisting postit tasks
		 * 
		 */
		
//		Tasks tsk = new DerbyTasks("resources/database/donkey-tasks-db");
		//new StickerTasks(tsk));
		
		//setting PersistentTasks configuration:
		//- derbytask decorated with derbytaskwithcolor
		PersistentTasks<PersistentTask> pTasks = new DerbyTasksWithColor(new SimpleDerbyTasks("resources/database/donkey-tasks-db"));
		PersistentTasks<PersistentTask> zTasks = new SimpleDerbyTasks("resources/database/donkey-tasks-db");
		//pTasks.add("New task " + new Date());
		
		//setting Printable configuration
		//
		PrintableTasks<PrintableTask> ptTasks = new SimpleStickerTasks(pTasks);
				
		SystemTrayApplication app = new SystemTrayApplication(ptTasks);
				
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
