package main;


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

import main.ui.swing.PostItTask;
import main.ui.swing.PostItTasks;
import persistence.DerbyTasks;

public class SwingApplication {
	
	private static PostItTasks tasks;
	
	public static void main (String [] args) throws Exception {
		
		if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
		
		final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("../images/bulb.gif", "tray icon"));
        

        final SystemTray tray = SystemTray.getSystemTray();
         
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem newItem = new MenuItem("New Task...");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(newItem);
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
         
//        ActionListener listener = new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                MenuItem item = (MenuItem)e.getSource();
//                //TrayIcon.MessageType type = null;
//                System.out.println(item.getLabel());
//                if ("Error".equals(item.getLabel())) {
//                    //type = TrayIcon.MessageType.ERROR;
//                    trayIcon.displayMessage("Sun TrayIcon Demo",
//                            "This is an error message", TrayIcon.MessageType.ERROR);
//                     
//                } else if ("Warning".equals(item.getLabel())) {
//                    //type = TrayIcon.MessageType.WARNING;
//                    trayIcon.displayMessage("Sun TrayIcon Demo",
//                            "This is a warning message", TrayIcon.MessageType.WARNING);
//                     
//                } else if ("Info".equals(item.getLabel())) {
//                    //type = TrayIcon.MessageType.INFO;
//                    trayIcon.displayMessage("Sun TrayIcon Demo",
//                            "This is an info message", TrayIcon.MessageType.INFO);
//                     
//                } else if ("None".equals(item.getLabel())) {
//                    //type = TrayIcon.MessageType.NONE;
//                    trayIcon.displayMessage("Sun TrayIcon Demo",
//                            "This is an ordinary message", TrayIcon.MessageType.NONE);
//                }
//            }
//        };

        newItem.addActionListener(new ActionListener() {
			
        	
			@Override			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Gecko");
                tasks.add(new PostItTask(new SimpleTask(8, "")));
			}
		});
         
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
		
	}
	
    //Obtain the image URL
    protected static Image createImage(String path, String description) throws Exception{
        
        InputStream is = SwingApplication.class.getResourceAsStream(path);
        Image image = ImageIO.read(is);
         
        if (image == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(image, description)).getImage();
        }
    }

    
    public SwingApplication() {
		tasks = new PostItTasks(new DerbyTasks());
	}
}
