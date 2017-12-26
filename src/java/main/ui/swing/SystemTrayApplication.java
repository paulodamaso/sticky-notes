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

import main.sticker.Sticker;
import main.sticker.ui.jdialog.JDialogStickers;

public final class SystemTrayApplication {

	private final JDialogStickers stickers;
	
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
        MenuItem saveAllItem = new MenuItem("Save All Tasks...");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(newTaskItem);
        popup.add(saveAllItem);
        popup.addSeparator();
        popup.add(exitItem);
         
        trayIcon.setPopupMenu(popup);

        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
         
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
         

        // action listener to newTaskItem: add a new sticker with a 
        // default task and print it
        newTaskItem.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				stickers.add("Type your text here").print();
			}
		});
        
        saveAllItem.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				for (Sticker stk : stickers.iterate()) {
//					stk.save();
				}
			}
		});

        // closing application, saves all stickers?
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//            	for (Task tsk : tasks.iterate()) {
//            		//tsk.
//            	}
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        stickers.print();
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


    public SystemTrayApplication(JDialogStickers stickers) {
		this.stickers = stickers;
	}
}
