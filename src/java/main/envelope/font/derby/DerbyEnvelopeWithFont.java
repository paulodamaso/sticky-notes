package main.envelope.font.derby;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.font.EnvelopeWithFont;
import main.note.Note;

/**
 * <p> {@link EnvelopeWithFont} implementations with color data in derby database, in table 'envelopewithfont'.
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopeWithFont implements EnvelopeWithFont {
	
	private final EnvelopeWithFont origin;
	private final String database;

	public DerbyEnvelopeWithFont(EnvelopeWithFont origin, String database) {
		this.origin = origin;
		this.database = database;
		
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
			
			Font font = origin.font();
			
			//saving font info
			PreparedStatement ps = null;
			
			if (font() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_font_query);
				ps.setString(1, origin.font().getFamily());
				ps.setInt(2, origin.font().getStyle());
				ps.setInt(3, origin.font().getSize());
				ps.setInt(4, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_font_query);
				ps.setInt(1, id());
				ps.setString(2, origin.font().getFamily());
				ps.setInt(3, origin.font().getStyle());
				ps.setInt(4, origin.font().getSize());
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
	public void print() {
		origin.print();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public Note persist(Note persistent) {
		persistFont();
		return origin.persist(persistent);
	}
}
