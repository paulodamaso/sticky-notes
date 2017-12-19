package persistence;

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
			/* @todo #12 implement better exception handling.
			 * 
			 */
			e.printStackTrace();
		}
	}

	
	//database: resources/donkey-tasks
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String iterate_query = "select id from task";
	@Override
	public Iterable<Task> iterate() {
		ArrayList<Task> it = new ArrayList<Task>();
		try {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(iterate_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				it.add(new DerbyTask(database, rs.getInt(0)));
			}
		}catch (Exception e) {
			/* @todo #12 implement better exception handling.
			 * 
			 */
			e.printStackTrace();
		}
		return it;
	}

	private final String insert_query = "insert into task (description) values (?)";
	@Override
	public Task add(String description) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query,  new String[] { "id"});
			ps.setString(0, description);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			return new DerbyTask(database, rs.getInt(0));
			
		}catch (Exception e) {
			/* @todo #12 implement better exception handling.
			 * 
			 */
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				/* @todo #12 implement better exception handling.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;
	}

}
