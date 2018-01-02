package main.note.persistence.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.note.Note;
import main.note.Notes;

public final class DerbyNotes implements Notes {
	private final String database;

	public DerbyNotes(String database) {
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

	private final String iterate_query = "select id from note";
	@Override
	public Collection<Note> iterate() {
		ArrayList<Note> it = new ArrayList<Note>();
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(iterate_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				it.add(new DerbyNote(database, rs.getInt(1)));
			}
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<Note>.
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

	private final String insert_query = "insert into note (text) values (?)";
	@Override
	public DerbyNote add(String description) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, description);
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return new DerbyNote(database, rs.getInt(1));
			
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when inserting note.
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
