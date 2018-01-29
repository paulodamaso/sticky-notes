package main.envelope.color.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;


/**
 * <p> {@link EnvelopeWithColor} repository in derby database, in table 'envelopewithcolor'
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopesWithColor implements EnvelopesWithColor {

	private final Envelopes origin;
	private final String database;

	public DerbyEnvelopesWithColor(Envelopes origin, String database) {
		this.origin = origin;
		this.database = database;
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	

	private final String iterate_color_query = "select id, red, green, blue from envelopewithcolor"; //$NON-NLS-1$

	public Collection<Envelope> iterate() {
		Collection<Envelope> it = origin.iterate();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<Envelope> toRemove = new ArrayList<Envelope>();
			Collection<Envelope> toAdd = new ArrayList<Envelope>();
			
			//notes with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting color in the envelope with color
			while(rs.next()) {
				for (Envelope env : it) {
					
					int id = rs.getInt(1);
					if (id == env.id()) {
						toRemove.add(env);
						toAdd.add(new DerbyEnvelopeWithColor(env, database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithColor.iterate
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
	public Collection<EnvelopeWithColor> iterateInColor() {
		Collection<Envelope> it = origin.iterate();
		Collection<EnvelopeWithColor> ret = new ArrayList<EnvelopeWithColor>();

		Connection conn = null;
		try {
			conn = connect();
			
			//notes with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting color in the envelope with color
			while(rs.next()) {
				for (Envelope stk : it) {

					int id = rs.getInt(1);
					if (id == stk.id()) {
						ret.add(new DerbyEnvelopeWithColor(stk, database));
					}
				}
			}

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithColor.iterateWithColor
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
	
	public Envelope add(String note) {
		return origin.add(note);
	}

	@Override
	public Envelopes origin() {
		return this.origin;
	}

}
