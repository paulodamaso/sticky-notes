package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Arrays;

import main.persistence.memory.MemoryTask;
import main.persistence.memory.MemoryTasks;
import main.ui.swing.TaskWithColor;

import main.ui.swing.SimpleStickerTask;
import main.ui.swing.TaskWithPosition;
import main.ui.swing.TaskWithSize;
import main.ui.swing.StickerTasks;
import main.ui.swing.SystemTrayApplication;
import main.ui.swing.TaskWithFont;

public class Main {

	public static void main(String[] args) throws Exception{
	

		/* @todo #6 implement a complete application, with taskbar icon and persisting postit tasks
		 * 
		 */
		
		SystemTrayApplication app = new SystemTrayApplication(new StickerTasks(
				new MemoryTasks(
				Arrays.asList(
						new TaskWithColor(
								new SimpleStickerTask(
										new MemoryTask(1, "Tis' a task with red color")
										) ,new Color(255, 0, 0)
								),
						new TaskWithFont(
								new SimpleStickerTask(
										new MemoryTask(2,  "A task with a beautiful font")
										),								
								new Font("Comic Sans MS", Font.BOLD, 24) 
								),
						new TaskWithPosition(
								new SimpleStickerTask(
										new MemoryTask(3,  "A task positioned in the middle of the screen")
										), Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350 / 2,Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 50
								),
						new TaskWithSize(
								new SimpleStickerTask(
										new MemoryTask(4,  "A big, beautiful task filling the whole screen")
										), Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height
								),
						new TaskWithFont (
								new TaskWithColor(
										new SimpleStickerTask(
												new MemoryTask(5, "A beautiful green task with a radical font")
												) ,new Color(0, 255, 0)
										), new Font("Segoe", Font.ITALIC, 20)
								)
						)
				)
			)
		);
		app.init();
		
		
		// TODO Auto-generated method stub

	}

}
