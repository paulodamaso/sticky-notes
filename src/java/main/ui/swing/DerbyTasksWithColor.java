package main.ui.swing;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Task;
import main.persistence.Persistent;
import main.persistence.jdbc.JdbcTask;
import main.persistence.jdbc.JdbcTasks;
import main.ui.swing.StickerTasks;

public final class DerbyTasksWithColor implements JdbcTasks<DerbyTaskWithColor> {
	
	private final JdbcTasks<JdbcTask> tasks;

	public DerbyTasksWithColor(JdbcTasks<JdbcTask> tasks) {
		this.tasks = tasks;
	}

	private final String iterate_color_query = "select id, red, green, blue from taskwithcolor";
	@Override
	public Iterable<DerbyTaskWithColor> iterate() {
		ArrayList<DerbyTaskWithColor> it = new ArrayList<DerbyTaskWithColor>();
		Connection conn = null;
		try {
			conn = tasks.connect();
			
			//tasks with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				for (JdbcTask tsk : tasks.iterate()) {
					int id = rs.getInt(1);
					if (id == tsk.id()) {
						it.add(new DerbyTaskWithColor(tsk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4))));
					}
				}
				
			}
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<DerbyTaskswithcolor>.
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

	

	/* 
	 * @ todo #12 trying to save this task, but what if derby not persistent ? review this derbytaskswithcolor
	 */
	@Override
	public void save() {
		for(Task tsk : iterate()) {
			try {
				((Persistent)tsk).save();
			} catch (Exception e) {
				System.out.println("Sorry, task not persistent");
			}
		}
	}


	@Override
	public Connection connect() throws Exception {
		return tasks.connect();
	}

	/*
	 * @todo #12 saving the color, but something looks wrong; maybe now it looks better, but
	 *  i don't like returning the object sent
	 */
	private final String insert_color_query = "insert into taskwithcolor (id, red, green, blue) values (?, ?, ?,  ?)";
	@Override
	public DerbyTaskWithColor add(DerbyTaskWithColor task) {
		Connection conn = null;
		try {
			//then, its color
			conn = tasks.connect();
			PreparedStatement ps = conn.prepareStatement(insert_color_query);
			ps.setInt(1, task.id());
			ps.setInt(2, task.color().getRed());
			ps.setInt(3, task.color().getGreen());
			ps.setInt(4, task.color().getBlue());
			ps.executeUpdate();

			
			return task;
			
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when inserting taskwithcolor.
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

	
	@Override
	public DerbyTaskWithColor add(String description) {
		return add(new DerbyTaskWithColor(tasks.add(description), new Color(255,247,64) ));
	}


}
