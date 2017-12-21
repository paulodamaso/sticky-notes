package main.persistence;

import main.Tasks;

/**
 * Top-level interface for {@link Tasks} persisted repositories.
 *  
 * @author paulodamaso
 *
 */
public interface PersistentTasks<T extends PersistentTask> extends Persistent, Tasks {

}
