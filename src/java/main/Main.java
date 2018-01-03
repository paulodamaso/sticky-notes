package main;

import main.note.Notes;
import main.note.persistence.derby.DerbyNotes;
import temp.ui.swing.SystemTrayApplication;

public class Main {

	public static void main(String[] args) throws Exception{
	
		/*
		 * @todo #46 externalize sticker configuration in properties file and create them using factories
		 */
		

		// new StickyNotesWithPosition( new StickyNotesWithColor (new StickyNotesWithFont( new StickyNotesWithSize( new PersistentEnvelopes (new Notes())))) )
		// new EnvelopesWithPosition (new EnvelopesWithColor ( new EnvelopesWithFont( new EnvelopesWithSize ( new DerbyEnvelopes (new DerbyNotes())))))
		

		Notes notes = new DerbyNotes("resources/database/sticky-notes-db");
		
		
//		Envelopes derbyEnvelopes= new DerbyEnvelopes(notes, "resources/database/sticky-notes-db");
//		Stickers stk = new main.ui.swing.jdialog.TextStickers(basic);
		
//		JDialogEnvelopes jDialogBasic = 
//				new JDialogEnvelopesWithPosition(
//			new JDialogEnvelopesWithFont( 
//				new JDialogEnvelopesWithColor(
//						new SimpleJDialogEnvelopes(derbyEnvelopes), 
//						new DerbyEnvelopesWithColor(derbyEnvelopes, "resources/database/sticky-notes-db")
//						), 
//				new DerbyEnvelopesWithFont(derbyEnvelopes, "resources/database/sticky-notes-db")
//			), new DerbyEnvelopesWithPosition(derbyEnvelopes, "resources/database/sticky-notes-db"));
//				
		SystemTrayApplication app = new SystemTrayApplication(notes);
				
		app.init();

	}

}
