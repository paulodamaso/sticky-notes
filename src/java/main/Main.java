package main;

import main.note.persistence.derby.DerbyNotes;

/**
 * <p> Main entry point for sticky-notes application.
 * 
 * @author paulodamaso
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{

		/*
		 * @todo #46 externalize note configuration in properties file and create them using factories
		 */
		new SystemTrayApplication(new DerbyNotes("resources/database/sticky-notes-db")).init();

	}

}
