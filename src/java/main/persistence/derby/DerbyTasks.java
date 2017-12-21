package main.persistence.derby;

import java.sql.Connection;

import main.Tasks;
import main.persistence.PersistentTasks;

/**
 * <p> Derby-persistent interface for {@link Tasks}
 * 
 * @author paulodamaso
 *
 */
public interface DerbyTasks<T extends DerbyTask> extends PersistentTasks<T> {
	
	public Connection connect() throws Exception;
	
	public T add(T task);
	
	@Override
	public T add(String description);

	@Override
	public Iterable<T> iterate();
}
