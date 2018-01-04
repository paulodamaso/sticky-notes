package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.SimpleEnvelope;
import main.envelope.SimpleEnvelopes;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.color.derby.DerbyEnvelopesWithColor;
import main.note.Notes;

/**
 * <p> System tray wrapper for sticky-notes envelopes.
 * 
 * @author paulodamaso
 *
 */
public final class SystemTrayApplication {
	
	/*
	 * @todo #50 internationalize SystemTrayApplication logged messages
	 */

	private final Notes notes;
	private final Envelopes<? extends Envelope> envelopes;
	private static final Logger logger = Logger.getLogger( SystemTrayApplication.class.getName() );

	
	/*
	 * @todo #46 should we pass the notes or envelopes to SystemTrayApplication ?
	 */
    public SystemTrayApplication(Notes notes) {
    	
    	this.notes = notes;
    	
//    	this.envelopes = 
//    			new DerbyEnvelopesWithSize(
//    				new DerbyEnvelopesWithPosition(
//    					new DerbyEnvelopesWithFont(
//    						new DerbyEnvelopesWithColor( 
//    							new SimpleEnvelopes(this.notes), 
//    						"resources/database/sticky-notes-db"), 
//	    				"resources/database/sticky-notes-db"),
//    				"resources/database/sticky-notes-db"),
//    			"resources/database/sticky-notes-db");
    	
    	Envelopes<? extends Envelope> env = new SimpleEnvelopes(this.notes);
    	
    	this.envelopes = new DerbyEnvelopesWithColor(env, "resources/database/sticky-notes-db");
    	
    	this.envelopes.add(new SimpleEnvelopeWithColor(new SimpleEnvelope(notes.add("Envelope com com enviado direto")), new Color (255,0,0)));
 
	}
	
	public void init() throws Exception {
		
		if (!SystemTray.isSupported()) {
			logger.severe("SystemTray is not supported");
            return;
        }
		

		final SystemTray tray = SystemTray.getSystemTray();
		final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("/images/sticky-note16x16.png", "tray icon"));

		/*
		 * @todo #50 internationalize system tray menu item labels 
		 */
        MenuItem aboutItem = new MenuItem("About");
        MenuItem newNoteItem = new MenuItem("New Sticky Note...");
        MenuItem saveAllItem = new MenuItem("Save All Sticky Notes...");
        MenuItem exitItem = new MenuItem("Exit");
         
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(newNoteItem);
        popup.add(saveAllItem);
        popup.addSeparator();
        popup.add(exitItem);
         
        trayIcon.setPopupMenu(popup);

        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
			logger.log(Level.SEVERE, "TrayIcon could not be added.", e);
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
		/*
		 * @todo #50 internationalize about menu item information 
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

        newNoteItem.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				envelopes.add(notes.add("Type your text here")).media().print();;
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
//        paint();
        
	}
	
//	protected void paint() {
//        for (Envelope envelope : envelopes.iterate()) {
//        	envelope.media().print();
//        }
//	}
	
//	protected void save() {
//
//        for (Envelope envelope : envelopes.iterate()) {
//        	envelope.persist(envelope);
//        }		
//	}
	
    protected static Image createImage(String path, String description) throws Exception{
        
        InputStream is = SystemTrayApplication.class.getResourceAsStream(path);
        Image image = ImageIO.read(is);
         
        if (image == null) {
			logger.severe("Resource not found: " + path);        	
            return null;
        } else {
            return (new ImageIcon(image, description)).getImage();
        }
    }
}
