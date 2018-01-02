package main.note;

import main.note.persistence.Persistent;


/**
 * <p> A top level representation of a note.
 * 
 * @author paulodamaso
 *
 */
public interface Note extends Persistent<Note>{

	public abstract int id();
	
	public abstract String text();

}
