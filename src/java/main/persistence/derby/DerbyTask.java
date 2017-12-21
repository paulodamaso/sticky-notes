package main.persistence.derby;

import java.sql.Connection;

import main.Task;
import main.persistence.PersistentTask;

/**
 * <p> Derby-persistent interface for {@link Task} 
 * 
 * @author paulodamaso
 *
 */
public interface DerbyTask extends  PersistentTask {
	
	
	public Connection connect() throws Exception;
}
