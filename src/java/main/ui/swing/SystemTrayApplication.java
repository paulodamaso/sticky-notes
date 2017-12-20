package main.ui.swing;



import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import main.Task;
import main.Tasks;
import main.persistence.derby.SimpleDerbyTasks;
import main.persistence.memory.MemoryTask;

public final class SystemTrayApplication {
	
	private final StickerTasks tasks;
	
	public void init() throws Exception {
		
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
		
		System.out.println("Creating popupmenu");
		
		final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("../../../images/bulb.gif", "tray icon"));
        

        final SystemTray tray = SystemTray.getSystemTray();
         
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem newTaskItem = new MenuItem("New Task...");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(newTaskItem);
        popup.addSeparator();
        popup.add(exitItem);
         
        trayIcon.setPopupMenu(popup);
        
        System.out.println("Setting trayicon");
        
        try {
            tray.add(trayIcon);
            System.out.println("Added trayicon");
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
         
        System.out.println("Adding listeners");
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });
         
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from the About menu item");
            }
        });
         

        newTaskItem.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
				new SimpleStickerTask(tasks.add("Escreva seu novo texto aqui")).print();
			}
		});
         
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	for (Task tsk : tasks.iterate()) {
            		//tsk.
            	}
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        System.out.println("Added listeners, printing tasks");
        
        tasks.print();
		
	}
	
    //Obtain the image URL
    protected static Image createImage(String path, String description) throws Exception{
        
        InputStream is = SystemTrayApplication.class.getResourceAsStream(path);
        Image image = ImageIO.read(is);
         
        if (image == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(image, description)).getImage();
        }
    }

    
    public SystemTrayApplication(Tasks tasks) {
		this.tasks = new StickerTasks(tasks);
	}
}
