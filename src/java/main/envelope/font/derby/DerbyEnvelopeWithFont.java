package main.envelope.font.derby;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.Messages;
import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;

/**
 * <p> {@link EnvelopeWithFont} implementations with color data in derby database, in table 'envelopewithfont'.
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopeWithFont implements EnvelopeWithFont {
	
	/*
	 * @todo #129 internationalize DerbyEnvelopeWithFont log messages
	 */
	private static final Logger logger = Logger.getLogger( DerbyEnvelopeWithFont.class.getName() );
	
	private final Envelope origin;
	private final String database;

	public DerbyEnvelopeWithFont(Envelope origin, String database) {
		this.origin = origin;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopeWithFont
			 * 
			 */
			e.printStackTrace();
		}
	}

	@Override
	public int id() {
		return origin.id();
	}

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private final String insert_font_query = "insert into envelopewithfont (id, name, style, size) values ( ?, ?, ?, ?)"; //$NON-NLS-1$
	private final String update_font_query = "update envelopewithfont set name = ?, style = ?, size = ? where id = ?"; //$NON-NLS-1$
	public DerbyEnvelopeWithFont font(Font font) {
		Connection conn = null;
		try {
			
					
			//saving font info
			PreparedStatement ps = null;
			
			if (font() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_font_query);
				ps.setString(1, font.getFamily());
				ps.setInt(2, font.getStyle());
				ps.setInt(3, font.getSize());
				ps.setInt(4, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_font_query);
				ps.setInt(1, id());
				ps.setString(2, font.getFamily());
				ps.setInt(3, font.getStyle());
				ps.setInt(4, font.getSize());
			}
	
			ps.executeUpdate();
			
			return new DerbyEnvelopeWithFont(origin, database);
	
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving JDialogEnvelopeWithFont
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving JDialogEnvelopeWithFont.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String font_query = "select name, style, size from envelopewithfont where id = ?"; //$NON-NLS-1$
	@Override
	public Font font() {
		Connection conn = null;
		Font font = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(font_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				font =  new Font(rs.getString(1), rs.getInt(2), rs.getInt(3));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving font from database
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving font from database
				 * 
				 */
				e.printStackTrace();
			}
		}
		return font;
	}
	
	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public void text(String text) {
		origin.text();
	}

	@Override
	public Envelope origin() {
		return origin;
	}

	private final String delete_font_query = "delete from envelopewithfont where id = ?"; //$NON-NLS-1$
	public void delete() {
		Connection conn = null;
		try {
			PreparedStatement ps = null;

			conn = connect();
			ps = conn.prepareStatement(delete_font_query);
			ps.setInt(1, id());
			ps.executeUpdate();
	
		}catch(Exception e) {
			logger.log(Level.SEVERE, Messages.getString("DerbyEnvelopeWithFont.errorDeleting"), e); //$NON-NLS-1$
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				logger.log(Level.SEVERE, Messages.getString("DerbyEnvelopeWithFont.errorClosinConnection"), e); //$NON-NLS-1$
			}
		}
		origin.delete();
	}
}
