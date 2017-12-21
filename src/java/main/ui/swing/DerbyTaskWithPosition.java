package main.ui.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.persistence.jdbc.JdbcTask;

public class DerbyTaskWithPosition implements JdbcTask {

	private final int x;
	private final int y;
	private final JdbcTask task;

	public DerbyTaskWithPosition(JdbcTask task, int x, int y) {
		this.x = x;
		this.y = y;
		this.task = task;
	}
	
	private final String save_position_query = "update taskwithposition set x = ?, y = ? where id = ?";
	@Override	
	public void save() {
		Connection conn = null;
		try {
			//saving origin first
			task.save();
			
			//saving color info
			conn = task.connect();
			PreparedStatement ps = conn.prepareStatement(save_position_query);
			ps.setInt(1, x);
			ps.setInt(2, y);
			ps.setInt(3, id());
			ps.executeUpdate();

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving derbytaskwithposition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving derbytaskwithposition
				 * 
				 */
				e.printStackTrace();
			}
		}
	}

	@Override
	public int id() {
		return task.id();
	}

	@Override
	public String description() {
		return task.description();
	}

	@Override
	public Connection connect() throws Exception {
		return task.connect();
	}
}
