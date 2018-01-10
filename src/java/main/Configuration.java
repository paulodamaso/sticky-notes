package main;

import main.note.Notes;
import main.note.persistence.derby.DerbyNotes;

public final class Configuration {

	public Configuration() {
		
	}
	
	public Notes notes () {
		return new DerbyNotes();
	}
	
	public Application application() {
		//return new CommandLineApplication(this);
		return new SystemTrayApplication(this);
	} 
	
	public String about() {
		return "Sticky-notes: sticky notes for donkeys\n\nby Paulo Lobo\n\nhttp://github.com/paulodamaso/sticky-notes/\n\n";
	}

}
