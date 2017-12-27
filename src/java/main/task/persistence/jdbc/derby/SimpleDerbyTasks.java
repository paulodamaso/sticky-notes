package main.task.persistence.jdbc.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.task.Task;
import main.task.Tasks;

/**
 * <p> Implemented Collection of {@link Task} persisted in a Derby database ('task' table). 
 * 
 * @author paulodamaso
 *
 */
public final class SimpleDerbyTasks implements Tasks {
	
	private final String database;

	public SimpleDerbyTasks(String database) {
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing derby driver from SimpleDerbyTasks
			 * 
			 */
			e.printStackTrace();
		}
	}
	

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String iterate_query = "select id from task";
	@Override
	public Iterable<Task> iterate() {
		ArrayList<Task> it = new ArrayList<Task>();
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(iterate_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				it.add(new SimpleDerbyTask(database, rs.getInt(1)));
			}
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<Task>.
			 * 
			 */
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return it;
	}

	private final String insert_query = "insert into task (description) values (?)";
	@Override
	public SimpleDerbyTask add(String description) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, description);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return new SimpleDerbyTask(database, rs.getInt(1));
			
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when inserting task.
			 * 
			 */
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private final String update_query = "update task set description = ? where id = ?";
	public Task save(Task task) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(update_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, task.description());
			ps.setInt(2, task.id());
			
			ps.executeUpdate();
			
			return new SimpleDerbyTask(database, task.id());
			
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when updating task.
			 * 
			 */
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
