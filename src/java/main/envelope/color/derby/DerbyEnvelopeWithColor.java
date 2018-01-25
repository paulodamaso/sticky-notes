package main.envelope.color.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;


/**
 * <p> {@link EnvelopeWithColor} implementation with color data in derby database, in table 'envelopewithcolor'.
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopeWithColor implements EnvelopeWithColor {
	
	/*
	 * @todo #129 internationalize DerbyEnvelopeWithColor log messages
	 */
	private static final Logger logger = Logger.getLogger( DerbyEnvelopeWithColor.class.getName() );
	
	private final Envelope origin;
	private final String database;

	public DerbyEnvelopeWithColor(Envelope origin, String database) {
		this.origin = origin;
		this.database = database;
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String color_query = "select  red, green, blue from envelopewithcolor where id = ?";
	@Override
	public Color color() {
		Connection conn = null;
		Color color = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(color_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				color =  new Color(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving color from derby
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving color from derby
				 * 
				 */
				e.printStackTrace();
			}
		}
		return color;
	}
	
	private final String insert_color_query = "insert into envelopewithcolor (id, red, green, blue) values ( ?, ?, ?, ?)";
	private final String update_color_query = "update envelopewithcolor set red = ?, green = ?, blue = ? where id = ?";
	public DerbyEnvelopeWithColor color(Color color) {
		Connection conn = null;
		try {
//			Color color = color;
			
			//saving color info
			PreparedStatement ps = null;
			
			if (color() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_color_query);
				ps.setInt(1, color.getRed());
				ps.setInt(2, color.getGreen());
				ps.setInt(3, color.getBlue());
				ps.setInt(4, origin.id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_color_query);
				ps.setInt(1, origin.id());
				ps.setInt(2, color.getRed());
				ps.setInt(3, color.getGreen());
				ps.setInt(4, color.getBlue());
			}
	
			ps.executeUpdate();
			
			return new DerbyEnvelopeWithColor(origin, database);
	
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving color information
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving color information
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public void text(String text) {
		origin.text(text);
	}

	@Override
	public Envelope origin() {
		return this.origin;
	}

	private final String delete_color_query = "delete from envelopewithcolor where id = ?";
	@Override
	public void delete() {
		Connection conn = null;
		try {
			PreparedStatement ps = null;
			
			conn = connect();
			ps = conn.prepareStatement(delete_color_query);
			ps.setInt(1, id());
	
			ps.executeUpdate();
	
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Error deleting envelope color", e);
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				logger.log(Level.SEVERE, "Error closing connection after envelope color", e);
			}
		}
		
		origin.delete();
	}
}
