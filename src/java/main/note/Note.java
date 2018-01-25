package main.note;

/**
 * <p> A top level representation of a note.
 * 
 * @author paulodamaso
 *
 */
public interface Note {

	public abstract int id();
	
	public abstract String text();
	
	public abstract void text(String text);
	
	public abstract void delete();

}
