package main.sticker.font.derby;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.sticker.Sticker;
import main.sticker.font.StickerWithFont;

/**
 * <p> {@link StickerWithFont} implementations with color data in derby database, in table 'stickerwithfont'.
 * 
 * @author paulodamaso
 *
 */
public class DerbyStickerWithFont implements StickerWithFont {
	
	private final Sticker origin;
	private final String database;
	private final Font font;

	public DerbyStickerWithFont(Sticker origin, Font font, String database) {
		this.origin = origin;
		this.font = font;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyStickerWithFont
			 * 
			 */
			e.printStackTrace();
		}
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return origin.text();
	}

	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}
	

	@Override	
	public DerbyStickerWithFont persist(Sticker sticker) {
	
		//delegating sticker saving behavior to origin, persisting font info only
		origin.persist(sticker);
		
		return new DerbyStickerWithFont(origin, persistFont(), database);

	}

	private final String insert_font_query = "insert into stickerwithfont (id, name, style, size) values ( ?, ?, ?, ?)";
	private final String update_font_query = "update stickerwithfont set name = ?, style = ?, size = ? where id = ?";
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
			/* @todo #12 implement better exception handling when saving JDialogStickerWithFont
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving JDialogStickerWithFont.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String font_query = "select name, style, size from stickerwithfont where id = ?";
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

}
