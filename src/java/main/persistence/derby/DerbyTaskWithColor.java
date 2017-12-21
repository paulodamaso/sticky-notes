package main.persistence.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.ui.TaskWithColor;

/**
 * <p> {@link DerbyTask} decorator for a task with color information saved in the database ('taskwithcolor' table)
 * 
 * @author paulodamaso
 *
 */
public final class DerbyTaskWithColor implements DerbyTask, TaskWithColor {
	
	private final Color color;
	private final DerbyTask task;

	public DerbyTaskWithColor(DerbyTask task, Color color) {
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
	
	private final String save_color_query = "update taskwithcolor set red = ?, green = ?, blue = ? where id = ?";
	@Override	
	public void save() {
		Connection conn = null;
		try {
			//saving origin task first
			task.save();
			
			//saving color info
			conn = task.connect();
			PreparedStatement ps = conn.prepareStatement(save_color_query);
			ps.setInt(1, color.getRed());
			ps.setInt(2, color.getGreen());
			ps.setInt(3, color.getBlue());
			ps.setInt(4, id());
			ps.executeUpdate();

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving derbytaskwithcolor
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving derbytaskwithcolor.
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
