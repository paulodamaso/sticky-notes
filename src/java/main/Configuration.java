package main;

import main.note.Notes;
import main.note.persistence.derby.DerbyNotes;

public class Configuration {

	public Configuration() {
		
	}
	
	public Notes notes () {
		return new DerbyNotes();
	}

}
