package main;

/**
 * <p> Main entry point for sticky-notes application.
 * 
 * @author paulodamaso
 *
 */
public class Main {
	


	public static void main(String[] args) throws Exception{
		
		Configuration configuration = new Configuration();

		/*
		 * @todo #46 externalize note configuration in properties file and create them using factories
		 */
		new SystemTrayApplication(configuration.notes()).init();

	}

}
