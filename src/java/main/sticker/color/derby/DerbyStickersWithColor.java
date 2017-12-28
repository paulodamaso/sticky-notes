package main.sticker.color.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;
import main.sticker.color.StickerWithColor;

/**
 * <p> {@link StickerWithColor} repository in derby database, in table 'stickerwithcolor'
 * 
 * @author paulodamaso
 *
 */
public final class DerbyStickersWithColor implements Stickers {
	
	private final Stickers origin;
	private final String database;

	public DerbyStickersWithColor(Stickers origin, String database) {
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
	
	@Override
	public Sticker add(String text) {
		return origin.add(text);
	}

	private final String iterate_color_query = "select id, red, green, blue from stickerwithcolor";
	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> it = origin.iterate();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<Sticker> toRemove = new ArrayList<Sticker>();
			Collection<Sticker> toAdd = new ArrayList<Sticker>();
			
			//stickers with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting color in the task with color
			while(rs.next()) {
				for (Sticker stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {
						toRemove.add(stk);
						toAdd.add(new DerbyStickerWithColor(stk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4)), database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyStickersWithColor.iterate
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
}
