package main.ui.swing;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.persistence.jdbc.JdbcTask;

/**
 * <p> {@link JdbcTask} decorator for a task with color information saved in the database ('taskwithcolor' table)
 * 
 * @author paulodamaso
 *
 */
public final class DerbyTaskWithColor implements JdbcTask, TaskWithColor {
	
	private final Color color;
	private final JdbcTask task;

	public DerbyTaskWithColor(JdbcTask task, Color color) {
		this.color = color;
		this.task = task;
	}
	
	private final String color_query = "select  red, green, blue from taskwithcolor where id = ?";
	@Override
	public Color color() {
		Connection conn = null;

		try {
			
			//saving color info
			conn = task.connect();
			PreparedStatement ps = conn.prepareStatement(color_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new Color(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}else {
				System.out.println("NO color yet!");
				return new Color(255,247,64);
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving color
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving color
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;
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
