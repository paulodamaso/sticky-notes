package main.envelope.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.note.Note;
import main.note.Notes;

public class DerbyEnvelopes implements Envelopes {
	
	private Notes notes;
	private String database;
	
	public DerbyEnvelopes(Notes notes, String database) {
		this.notes = notes;
		this.database = database;
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}


	private final String iterate_query = "select id , note from envelope";
	@Override
	public Collection<Envelope> iterate() {
		ArrayList<Envelope> it = new ArrayList<Envelope>();
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(iterate_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				for(Note note : notes.iterate()) {
					if (note.id() == rs.getInt(2)) {
						it.add(new DerbyEnvelope(rs.getInt(1), note, database));
					}
				}
				
			}
		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Collection<Envelope>.
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

	private final String insert_query = "insert into envelope (note) values (?)";
	@Override
	public DerbyEnvelope add(Note note) {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(insert_query,  PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, note.id());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			return new DerbyEnvelope(rs.getInt(1), note, database );
			
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
