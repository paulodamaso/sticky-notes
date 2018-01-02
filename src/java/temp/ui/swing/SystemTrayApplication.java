package temp.ui.swing;

import java.awt.AWTException;
import java.awt.Dimension;
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.color.derby.DerbyEnvelopesWithColor;
import main.envelope.font.derby.DerbyEnvelopesWithFont;
import main.envelope.sticky.SimpleStickyJDialogEnvelopes;
import main.envelope.sticky.color.StickyJDialogEnvelopesWithColor;
import main.envelope.sticky.font.StickyJDialogEnvelopesWithFont;
import main.note.Notes;




public final class SystemTrayApplication {

	private final Notes notes;
	private final Envelopes envelopes;
	
	public void init() throws Exception {
		
		if (!SystemTray.isSupported()) {
            //System.out.println("SystemTray is not supported");
            return;
        }
		
		//checking trayicon image size
		final SystemTray tray = SystemTray.getSystemTray();
		Dimension trayIconSize = tray.getTrayIconSize();
		//System.out.println(trayIconSize);
		
		
		//System.out.println("Creating popupmenu");
		
		final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("/images/sticky-note16x16.png", "tray icon"));
        

        
         
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem newTaskItem = new MenuItem("New Sticker...");
        MenuItem saveAllItem = new MenuItem("Save All Sticker...");
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
            //System.out.println("TrayIcon could not be added.");
            return;
        }
         
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });
         
        
        /*
         * @todo #48 add relevant information in about dialog
         */
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from the About menu item");
            }
        });
        
        /*
         * @todo #48 implement show all menu item in task bar icon
         */
         

        // action listener to newTaskItem: add a new sticker with a 
        // default task and print it
        newTaskItem.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				envelopes.envelope(notes.add("Type your text here")).print();
			}
		});
        
        /*
         * @todo #48 should we implement a save all menuitem in task bar icon?
         */
//        saveAllItem.addActionListener(new ActionListener() {
//			@Override			
//			public void actionPerformed(ActionEvent e) {
//				for (Sticker stk : stickers.iterate()) {
//					stk.persist(stk);
//				}
//			}
//		});

        /*
         * @todo #48 should we save all stickers on closing application?
         */
//        // closing application, saves all stickers?
//        exitItem.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//				for (Sticker stk : stickers.iterate()) {
////					stk.persist(stk);
//				}
//                tray.remove(trayIcon);
//                System.exit(0);
//            }
//        });
//       
//

        for (Envelope envelope : envelopes.envelopes()) {
        	envelope.print();
        }
        
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


    public SystemTrayApplication(Notes notes) {
    	this.notes = notes;
    	//this.envelopes = new ConsoleEnvelopes(notes);
    	//this.envelopes = new SimpleStickyJDialogEnvelopes(notes);
    	Envelopes basicEnvelopes = new SimpleStickyJDialogEnvelopes(notes);
    	
    	this.envelopes =
    			new StickyJDialogEnvelopesWithFont(
    					new StickyJDialogEnvelopesWithColor( //jdialogswithcolor
    							new SimpleStickyJDialogEnvelopes(notes), //jdialoginfo 
    						new DerbyEnvelopesWithColor(basicEnvelopes, "resources/database/sticky-notes-db") //color info
    							),
    					new DerbyEnvelopesWithFont(basicEnvelopes, "resources/database/sticky-notes-db") //font info
    					); 
	}
}
