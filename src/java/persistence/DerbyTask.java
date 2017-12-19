package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Task;

public final class DerbyTask implements Task {
	
	private final String database;
	private final int id;
	
	public DerbyTask(String database, int id) {
		this.database = database;
		this.id = id;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing derby driver
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	//database: resources/donkey-tasks
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	@Override
	public int id() {
		return id;
	}

	private final String description_query = "select description from task where id = ?";
	@Override
	public String description() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(description_query);
			ResultSet rs = ps.executeQuery();
			return rs.getString(0);
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

}
