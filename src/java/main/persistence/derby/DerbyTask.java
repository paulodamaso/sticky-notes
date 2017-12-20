package main.persistence.derby;

import java.sql.Connection;

import main.Persistent;
import main.Task;

/**
 * <p> Derby-persisten interface for {@link Task} 
 * 
 * @author paulodamaso
 *
 */
public interface DerbyTask extends  Task, Persistent {
	
	
	public Connection connect() throws Exception;
}
