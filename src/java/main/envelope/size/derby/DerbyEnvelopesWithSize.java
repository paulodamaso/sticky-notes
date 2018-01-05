package main.envelope.size.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;
import main.note.Note;

/**
 * <p> {@link EnvelopeWithSize} repository in derby database, in table 'envelopewithsize'
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopesWithSize implements EnvelopesWithSize {
	
	private final String database;
	private final Envelopes<? extends Envelope> origin;

	public DerbyEnvelopesWithSize(Envelopes<? extends Envelope> origin, String database) {

		this.origin = origin;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopesWithSize
			 * 
			 */
			e.printStackTrace();
		}		
	}

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}
	
	private final String iterate_size_query = "select id, width, height from envelopewithsize";
	@Override
	public Collection<Envelope> iterate() {

		Collection<Envelope> it = origin.iterate();
		
		Collection<Envelope> toRemove = new ArrayList<Envelope>();
		Collection<Envelope> toAdd = new ArrayList<Envelope>();
		
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_size_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all notes, setting size in the note with size
			while(rs.next()) {
				for (Envelope stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						toRemove.add(stk);
						toAdd.add(new DerbyEnvelopeWithSize(stk, database));
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

	@Override
	public Envelope add(Note note) {
		return origin.add(note);
	}

	@Override
	public Collection<EnvelopeWithSize> iterateInSize() {
		Collection<Envelope> it = origin.iterate();
		Collection<EnvelopeWithSize> ret = new ArrayList<EnvelopeWithSize>();

		Connection conn = null;
		try {
			conn = connect();
			
			//notes with color
			PreparedStatement ps = conn.prepareStatement(iterate_size_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting color in the envelope with color
			while(rs.next()) {
				for (Envelope stk : it) {

					int id = rs.getInt(1);
					if (id == stk.id()) {
						ret.add(new DerbyEnvelopeWithSize(stk, database));
					}
				}
			}

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithSize.iterateInSize
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
	public <T extends EnvelopeWithSize> Envelope add(EnvelopeWithSize envelope) {
		origin.add(envelope.origin());
		DerbyEnvelopeWithSize derby = new DerbyEnvelopeWithSize(envelope.origin(), database);
		return derby.size(envelope.size());
	}

}
