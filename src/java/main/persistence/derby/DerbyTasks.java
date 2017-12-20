package main.persistence.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Task;
import main.Tasks;

/**
 * <p> Task save in a Derby database.
 * 
 * @author paulodamaso
 *
 */
public final class DerbyTasks implements Tasks {
	
	private final String database;

	public DerbyTasks(String database) {
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing derby driver
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
				it.add(new DerbyTask(database, rs.getInt(1)));
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
	public Task add(String description) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, description);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return new DerbyTask(database, rs.getInt(1));
			
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

}
