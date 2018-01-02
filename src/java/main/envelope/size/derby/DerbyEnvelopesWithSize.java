package main.envelope.size.derby;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.size.EnvelopeWithSize;
import main.note.Envelope;
import main.note.Envelopes;

/**
 * <p> {@link EnvelopeWithSize} repository in derby database, in table 'notewithsize'
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopesWithSize implements Envelopes {
	
	private final String database;
	private final Envelopes origin;

	public DerbyEnvelopesWithSize(Envelopes origin, String database) {

		this.origin = origin;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogEnvelopesWithPosition
			 * 
			 */
			e.printStackTrace();
		}		
	}

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	@Override
	public Envelope add(String text) {
		return origin.add(text);
	}
	
	private final String iterate_position_query = "select id, width, height from notewithsize";
	@Override
	public Collection<Envelope> iterate() {

		Collection<Envelope> it = origin.iterate();
		
		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
		
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_position_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all notes, setting size in the note with size
			while(rs.next()) {
				for (Envelope stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						toRemove.add(stk);
						toAdd.add(new DerbyEnvelopeWithSize(stk, new Dimension(rs.getInt(2), rs.getInt(3)), database));
					}
				}
			}
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithSize.iterate.
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

}
