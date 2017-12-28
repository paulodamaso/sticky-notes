package main.sticker.font.derby;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;
import main.sticker.font.StickerWithFont;

/**
 * <p> {@link StickerWithFont} repository in derby database, in table 'stickerwithfont'
 * 
 * @author paulodamaso
 *
 */
public final class DerbyStickersWithFont implements Stickers {

	private final Stickers origin;
	private final String database;
	
	public DerbyStickersWithFont(Stickers origin, String database) {
		this.origin = origin;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyStickersWithColor
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	private final String iterate_font_query = "select id, name, style, size from stickerwithfont";
	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> it = origin.iterate();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<Sticker> toRemove = new ArrayList<Sticker>();
			Collection<Sticker> toAdd = new ArrayList<Sticker>();
			
			//tasks with font
			PreparedStatement ps = conn.prepareStatement(iterate_font_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting font in the task with font
			while(rs.next()) {
				for (Sticker stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {

						toRemove.add(stk);
						toAdd.add(new DerbyStickerWithFont(stk, new Font(rs.getString(2), rs.getInt(3), rs.getInt(4)), database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyStickersWithFont.iterate
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
	public Sticker add(String text) {
		return origin.add(text);
	}

}
