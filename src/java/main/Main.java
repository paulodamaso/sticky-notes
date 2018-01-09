package main;

import console.CommandLineApplication;

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
		Configuration configuration = new Configuration();
		
//		Application app = new SystemTrayApplication(configuration.notes());
		
		Application app = new CommandLineApplication(configuration.notes()).init();
	}

}
