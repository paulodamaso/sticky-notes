package main.persistence.derby;

import java.sql.Connection;

import main.Tasks;

/**
 * <p> Derby-persistent interface for {@link Tasks}
 * 
 * @author paulodamaso
 *
 */
public interface DerbyTasks extends PersistentTasks {
	
	public Connection connect() throws Exception;

}
