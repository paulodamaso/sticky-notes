package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.SimpleTask;
import main.Task;
import main.Tasks;

/**
 * <p> Task save in a Derby database.
 * 
 * @author paulodamaso
 *
 */
public final class DerbyTasks implements Tasks {
	
//	private final Tasks tasks;

	public DerbyTasks() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
		}catch (Exception e){
			/* @todo #12 implement better exception handling.
			 * 
			 */
			e.printStackTrace();
		}
	}

	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:resources/donkey-tasks;");
	}

//	private final String select_query = "select * from task where id=?";
//	@Override
//	public void save() {
//		try {
//			Connection conn = connect();
//			PreparedStatement ps = conn.prepareStatement(select_query);
//			ps.setInt(0, this.id);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				it.add(new SimpleTask(rs.getInt("id"), rs.getString("description")));
//			}
//		}catch (Exception e) {
//			/* @todo #12 implement exception handling.
//			 * 
//			 */
//		}
//		return it;
//
//	}

	private final String iterate_query = "select * from task";
	@Override
	public Iterable<Task> iterate() {
		ArrayList<Task> it = new ArrayList<Task>();
		try {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(iterate_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				it.add(new SimpleTask(rs.getInt("id"), rs.getString("description")));
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
	public Tasks add(Task task) {
		try {
			Connection conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query);
			ps.setString(0, task.description());
			ps.executeUpdate();

		}catch (Exception e) {
			/* @todo #12 implement better exception handling.
			 * 
			 */
			e.printStackTrace();
		}
		return this;
	}

}
