package main.envelope.position.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;
import main.note.Note;

/**
 * <p> {@link EnvelopeWithPosition} repository in derby database, in table 'envelopewithposition'
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopesWithPosition implements EnvelopesWithPosition {
	
	private final String database;
	private final Envelopes<? extends Envelope> origin;

	public DerbyEnvelopesWithPosition(Envelopes<? extends Envelope> origin, String database) {

		this.origin = origin;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopesWithPosition
			 * 
			 */
			e.printStackTrace();
		}		
	}

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	
	private final String iterate_position_query = "select id, x, y from envelopewithposition";
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
			
			//iterate in all envelopes, setting position in the note with position
			while(rs.next()) {
				for (Envelope stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						toRemove.add(stk);
						toAdd.add(new DerbyEnvelopeWithPosition(stk, database));
					}
				}
			}
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithPosition.iterate
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

	@Override
	public Collection<EnvelopeWithPosition> iterateInPosition() {
		Collection<Envelope> it = origin.iterate();
		Collection<EnvelopeWithPosition> ret = new ArrayList<EnvelopeWithPosition>();
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_position_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting position in the note with position
			while(rs.next()) {
				for (Envelope stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						ret.add(new DerbyEnvelopeWithPosition(stk, database));
					}
				}
			}


		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithPosition.iterateInPosition
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
		return ret;
	}

	@Override
	public Envelope add(Note note) {
		return origin.add(note);
	}

	@Override
	public <T extends EnvelopeWithPosition> Envelope add(EnvelopeWithPosition envelope) {
		origin.add(envelope.origin());
		DerbyEnvelopeWithPosition derby = new DerbyEnvelopeWithPosition(envelope.origin(), database);
		return derby.position(envelope.position());
	}


}
