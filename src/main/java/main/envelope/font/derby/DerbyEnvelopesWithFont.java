package main.envelope.font.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;

/**
 * <p> {@link EnvelopeWithFont} repository in derby database, in table 'envelopewithfont'
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopesWithFont implements EnvelopesWithFont {

	private final Envelopes origin;
	private final String database;
	
	public DerbyEnvelopesWithFont(Envelopes origin, String database) {
		this.origin = origin;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopesWithFont
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private final String iterate_font_query = "select id, name, style, size from envelopewithfont"; //$NON-NLS-1$
	@Override
	public Collection<Envelope> iterate() {
		Collection<Envelope> it = origin.iterate();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<Envelope> toRemove = new ArrayList<Envelope>();
			Collection<Envelope> toAdd = new ArrayList<Envelope>();
			
			//envelopes with font
			PreparedStatement ps = conn.prepareStatement(iterate_font_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting font in the envelope with font
			while(rs.next()) {
				for (Envelope stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {
						toRemove.add(stk);
						toAdd.add(new DerbyEnvelopeWithFont(stk,  database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithFont.iterate
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
	public Collection<EnvelopeWithFont> iterateInFont() {
		Collection<Envelope> it = origin.iterate();
		Collection<EnvelopeWithFont> ret = new ArrayList<EnvelopeWithFont>();

		Connection conn = null;
		try {
			conn = connect();
			
			//envelopes with font
			PreparedStatement ps = conn.prepareStatement(iterate_font_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all envelopes, setting font in the envelope with font
			while(rs.next()) {
				for (Envelope stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {

						ret.add(new DerbyEnvelopeWithFont(stk,  database));
					}
				}
			}


		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyEnvelopesWithFont.iterateInFont
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
	public Envelope add(String note) {
		return origin.add(note);
	}

	@Override
	public Envelopes origin() {
		return origin;
	}
}
