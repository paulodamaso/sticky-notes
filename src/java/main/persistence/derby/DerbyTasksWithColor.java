package main.persistence.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Persistent;
import main.Task;
import main.Tasks;

public final class DerbyTasksWithColor implements DerbyTasks {
	
	private final DerbyTasks tasks;

	public DerbyTasksWithColor(DerbyTasks tasks) {
		this.tasks = tasks;
	}

	private final String iterate_color_query = "select id, reg, green, blue from taskwithcolor";
	@Override
	public Iterable<Task> iterate() {
		ArrayList<Task> it = new ArrayList<Task>();
		Connection conn = null;
		try {
			conn = tasks.connect();
			
			//tasks with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				for (Task tsk : iterate()) {
					int id = rs.getInt(1);
					if (id == tsk.id()) {
						it.add(new DerbyTaskWithColor((DerbyTask)tsk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4))));
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
	 * @todo #12 one-million dollar question, how save the color?
	 */
	private final String insert_color_query = "insert into taskwithcolor (id, red, green, blue) values (?, ?, ?,  ?)";
	@Override
	public Task add(String description) {
		Connection conn = null;
		try {
			//first insert the derby task
			Task tsk = tasks.add(description);
			
			//then, its color
			conn = tasks.connect();
			PreparedStatement ps = conn.prepareStatement(insert_color_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tsk.id());
			ps.setInt(2, tsk.id());
			ps.setInt(3, tsk.id());
			ps.setInt(4, tsk.id());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return new DerbyTask(database, rs.getInt(1));
			
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


}
