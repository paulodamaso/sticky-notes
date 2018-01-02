package main.envelope.color.derby;

import java.awt.Color;
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
import main.envelope.color.SimpleEnvelopeWithColor;
import main.note.Note;


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
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopesWithColor
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String iterate_color_query = "select id, red, green, blue from envelopewithcolor";
	@Override
	public Collection<Envelope> envelopes() {
		Collection<Envelope> it = origin.envelopes();

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
						toAdd.add(new DerbyEnvelopeWithColor(new SimpleEnvelopeWithColor (env, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4))), database));
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
		Collection<Envelope> it = origin.envelopes();
		Collection<EnvelopeWithColor> ret = new ArrayList<EnvelopeWithColor>();
		

		Connection conn = null;
		try {
			conn = connect();
			
			//notes with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting color in the task with color
			while(rs.next()) {
				for (Envelope stk : it) {

					int id = rs.getInt(1);
					if (id == stk.id()) {
						ret.add(new DerbyEnvelopeWithColor(new SimpleEnvelopeWithColor(stk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4))), database));
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

	@Override
	public Envelope envelope(Note note) {
		return origin.envelope(note);
	}
}
