package main;

import main.note.NoteFactory;
import main.note.Notes;
import main.note.persistence.derby.DerbyNoteFactory;

public final class Configuration {
	
	private final NoteFactory noteFactory;

	public Configuration() {
		noteFactory = new DerbyNoteFactory("resources/database/sticky-notes-db"); 
		
	}
	
	public Notes notes () {
		return noteFactory.create();
	}
	
	public Application application() {
		//return new CommandLineApplication(this);
		return new SystemTrayApplication(this);
	} 
	
	public String about() {
		return "Sticky-notes: sticky notes for donkeys\n\nby Paulo Lobo\n\nhttp://github.com/paulodamaso/sticky-notes/\n\n";
	}

}
