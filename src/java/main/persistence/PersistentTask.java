package main.persistence;

import main.Task;

 /**
  * <p> Interface for representing a persistent task.
  * 
  * @author paulodamaso
  *
  */
public interface PersistentTask extends Persistent<Task>, Task {

	/**
	 * <p> Persist a {@link Task}.
	 * 
	 * @param task
	 * @return
	 */
	public Task persist(Task task);
}
