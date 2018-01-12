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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import main.envelope.Envelope;
import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.SimpleEnvelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.envelope.color.derby.DerbyEnvelopeWithColorFactory;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.envelope.font.derby.DerbyEnvelopeWithFontFactory;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;
import main.envelope.position.derby.DerbyEnvelopeWithPositionFactory;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;
import main.envelope.size.derby.DerbyEnvelopeWithSizeFactory;
import main.note.Notes;
import ui.MediaFactory;
import ui.PrintMedia;
import ui.sticky.JDialogSticky;
import ui.sticky.JDialogStickyMediaFactory;

/**
 * <p> System tray wrapper for sticky-notes envelopes.
 * 
 * @author paulodamaso
 *
 */
public final class SystemTrayApplication implements  Application {
	
	/*
	 * @todo #50 internationalize SystemTrayApplication logged messages
	 */
	private static final Logger logger = Logger.getLogger( SystemTrayApplication.class.getName() );
	
	/*
	 * @todo #119 Add persistence and factory info to Configuration interface
	 *  
	 */
	private final Notes notes;
	private final Configuration config;
	private final Envelopes envelopes;
	private final EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory = new DerbyEnvelopeWithColorFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory = new DerbyEnvelopeWithFontFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory = new DerbyEnvelopeWithPositionFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory = new DerbyEnvelopeWithSizeFactory("resources/database/sticky-notes-db");
	private final MediaFactory mediaFactory = new JDialogStickyMediaFactory(this);
	
    public SystemTrayApplication(Configuration config) {
		this.config = config;
		this.notes = this.config.notes();
		
    	this.envelopes = 
    			sizeFactory.createEnvelopes(
    					positionFactory.createEnvelopes(
    							fontFactory.createEnvelopes(
    									colorFactory.createEnvelopes(
    											new SimpleEnvelopes(this.notes)
    											)
    									)
    							)
    					);
	}
	
	public Application start() throws Exception {
		
		if (!SystemTray.isSupported()) {
			logger.severe("SystemTray is not supported");
            return this;
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
            return this;
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
                        config.about());
            }
        });
        
        /*
         * @todo #48 implement show all menu item in task bar icon
         */


        /*
         * @todo #48 make sticky note visible after creation
         */
        newNoteItem.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {
				Envelope newEnvelope = envelopes.add("Insert your text here!");
				JDialogSticky pm =	(JDialogSticky) mediaFactory.create(newEnvelope);
				newEnvelope.print(pm);
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
        // closing application, saves all stickers?
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        printAll();

        return this;
	}
	
	public void printAll () {
		for (Envelope enve : envelopes.iterate()) {
			PrintMedia pm =	mediaFactory.create(enve);
			enve.print(pm);
		}
	} 

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

	@Override
	public Envelopes envelopes() {
		return envelopes;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory() {
		return colorFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory() {
		return fontFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory() {
		return positionFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory() {
		return sizeFactory;
	}

	@Override
	public MediaFactory mediaFactory() {
		return mediaFactory;
	}
	
}
