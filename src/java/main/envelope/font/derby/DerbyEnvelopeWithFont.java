package main.envelope.font.derby;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.media.MediaFactoryImpl;
import main.envelope.media.PrintMedia;
import main.note.Note;

/**
 * <p> {@link EnvelopeWithFont} implementations with color data in derby database, in table 'envelopewithfont'.
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopeWithFont implements EnvelopeWithFont {
	
	private final Envelope origin;
	private final String database;
	private final Font font;

	public DerbyEnvelopeWithFont(Envelope origin, Font font, String database) {
		this.origin = origin;
		this.database = database;
		this.font = font;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
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
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String insert_font_query = "insert into envelopewithfont (id, name, style, size) values ( ?, ?, ?, ?)";
	private final String update_font_query = "update envelopewithfont set name = ?, style = ?, size = ? where id = ?";
	private Font persistFont() {
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
			
			return font;
	
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
	
	private final String font_query = "select name, style, size from envelopewithfont where id = ?";
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
	public PrintMedia media() {
		return new MediaFactoryImpl().create(this, origin.media());
	}

	@Override
	public void text(String text) {
		origin.text();
	}
}
