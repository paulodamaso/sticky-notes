package temp.envelope.position.derby;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.note.Note;
import temp.envelope.Envelope;
import temp.envelope.position.EnvelopeWithPosition;

public final class DerbyEnvelopeWithPosition implements EnvelopeWithPosition {

	private final Envelope origin;
	private final Point position;
	private final String database;
	
	public DerbyEnvelopeWithPosition(Envelope origin, Point position, String database) {
		this.origin = origin;
		this.position = position;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogEnvelopeWithPosition
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public DerbyEnvelopeWithPosition persist(Envelope note) {
		//delegating note saving behavior to origin, persisting position only
		origin.persist(note);
		
		return new DerbyEnvelopeWithPosition(origin, persistPosition(), database);
	}

	private final String insert_position_query = "insert into envelopewithposition (id, x, y) values ( ?, ?, ?)";
	private final String update_position_query = "update envelopewithposition set x = ?, y = ? where id = ?";
	private Point persistPosition () {
		Connection conn = null;
		try {
			
			//saving position info
			PreparedStatement ps = null;
			
			if (position() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_position_query);
				ps.setInt(1, position.x);
				ps.setInt(2, position.y);
				ps.setInt(3, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_position_query);
				ps.setInt(1, id());
				ps.setInt(2, position.x);
				ps.setInt(3, position.y);
			}

			ps.executeUpdate();
			
			return position;

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving DerbyEnvelopeWithPosition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving DerbyEnvelopeWithPosition.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String position_query = "select x, y from envelopewithposition where id = ?";
	@Override
	public Point position() {
		Connection conn = null;
		Point position = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(position_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				position =  new Point(rs.getInt(1), rs.getInt(2));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving position in DerbyEnvelopeWithPosition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving position in DerbyEnvelopeWithPosition
				 * 
				 */
				e.printStackTrace();
			}
		}
		return position;
	}

	@Override
	public Note note() {
		return origin.note();
	}

}
