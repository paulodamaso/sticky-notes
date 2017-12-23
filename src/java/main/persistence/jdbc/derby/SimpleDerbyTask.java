package main.persistence.jdbc.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Task;
import main.persistence.PersistentTask;

/**
 * <p> Simple derby {@link Task} implementation ('task' table)
 * 
 * @author paulodamaso
 *
 */
public final class SimpleDerbyTask implements PersistentTask {
	
	private final String database;
	private final int id;
	
	public SimpleDerbyTask(String database, int id) {
		this.database = database;
		this.id = id;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing derby driver here
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String description_query = "select description from task where id = ?";
	@Override
	public String description() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(description_query);
			ps.setInt(1, id());
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when returning description.
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling here.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;
	}

	private final String save_query = "update task set description = ? where id = ?";
	@Override
	public SimpleDerbyTask persist(Task task) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(save_query);
			ps.setString(1, task.description());
			ps.setInt(2, this.id());
			ps.executeUpdate();

			return new SimpleDerbyTask(database, this.id());
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving derbytask
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving derbytask.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return id;
	}

}