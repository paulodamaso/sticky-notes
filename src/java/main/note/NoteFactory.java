package main.note;

/**
 * <p> Abstraction of a persistence factory for {@link Note} and {@link Notes}.
 *  
 * @author paulodamaso
 *
 */
public interface NoteFactory {

	 /**
	  * <p> Checks if the repository is ready to run.
	  * @return
	  */
	public boolean check();
	
	/**
	 * <p> Initialize the repository.
	 */
	public void init();
	
	/**
	 * <p> Create a {@link Notes} repository.
	 * 
	 * @return
	 */
	public Notes create();
	
}
