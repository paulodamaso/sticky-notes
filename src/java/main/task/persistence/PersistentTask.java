package main.task.persistence;

import main.task.Task;

/**
 * <p> Interface defining behavior for persistent objects 
 * 
 * @author paulodamaso
 *
 */
public interface PersistentTask extends Task{

	public PersistentTask persist(Task task);
}
