package main.persistence;

import main.Task;

/**
 * <p> Interface defining behavior for persistent objects 
 * 
 * @author paulodamaso
 *
 */
public interface PersistentTask extends Task{

	public PersistentTask persist(Task task);
}
