package main;

import main.envelope.Envelopes;
import main.envelope.color.derby.DerbyEnvelopesWithColor;
import main.envelope.font.derby.DerbyEnvelopesWithFont;
import main.envelope.jdialog.JDialogEnvelopes;
import main.envelope.jdialog.SimpleJDialogEnvelopes;
import main.envelope.jdialog.color.JDialogEnvelopesWithColor;
import main.envelope.jdialog.font.JDialogEnvelopesWithFont;
import main.envelope.jdialog.position.JDialogEnvelopesWithPosition;
import main.envelope.persistence.DerbyEnvelopes;
import main.envelope.position.derby.DerbyEnvelopesWithPosition;
import main.note.Notes;
import main.note.persistence.derby.DerbyNotes;
import main.ui.swing.SystemTrayApplication;

public class Main {

	public static void main(String[] args) throws Exception{
	
		/*
		 * @todo #46 externalize sticker configuration in properties file and create them using factories
		 */
		
		//first data, then presentation
		//stcker / persistence
		//all stickers
		Notes notes = new DerbyNotes("resources/database/sticky-notes-db"); 
		
		Envelopes derbyEnvelopes= new DerbyEnvelopes(notes, "resources/database/sticky-notes-db");
//		Stickers stk = new main.ui.swing.jdialog.TextStickers(basic);
		
		JDialogEnvelopes jDialogBasic = 
				new JDialogEnvelopesWithPosition(
			new JDialogEnvelopesWithFont( 
				new JDialogEnvelopesWithColor(
						new SimpleJDialogEnvelopes(derbyEnvelopes), 
						new DerbyEnvelopesWithColor(derbyEnvelopes, "resources/database/sticky-notes-db")
						), 
				new DerbyEnvelopesWithFont(derbyEnvelopes, "resources/database/sticky-notes-db")
			), new DerbyEnvelopesWithPosition(derbyEnvelopes, "resources/database/sticky-notes-db"));
				
		SystemTrayApplication app = new SystemTrayApplication(jDialogBasic, notes);
				
		app.init();

	}

}
