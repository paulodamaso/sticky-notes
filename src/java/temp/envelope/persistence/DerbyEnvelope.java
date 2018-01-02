package temp.envelope.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import main.note.Note;
import temp.envelope.Envelope;

public final class DerbyEnvelope implements Envelope {
	
	private final int id;
	private final Note note;
	private final String database;
	
	public DerbyEnvelope(int id, Note note, String database) {
		this.id = id;
		this.note = note;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing derby driver here
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	public Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String save_query = "update envelope set note = ? where id = ?";
	@Override
	public DerbyEnvelope persist(Envelope persistent) {
		//saving note information
		note.persist(persistent.note());
		
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(save_query);
			ps.setInt(1, this.note().id());
			ps.setInt(2, this.id());
			ps.executeUpdate();

			return new DerbyEnvelope(this.id(), persistent.note(), database);
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving derbyenvelope
			 * 
			 */
			e.printStackTrace();
			
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving derbynote
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Note note() {
		return note;
	}

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return id;
	}

}
