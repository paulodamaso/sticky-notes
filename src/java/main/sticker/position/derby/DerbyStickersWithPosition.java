package main.sticker.position.derby;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;
import main.sticker.position.StickerWithPosition;

/**
 * <p> {@link StickerWithPosition} repository in derby database, in table 'stickerwithposition'
 * 
 * @author paulodamaso
 *
 */
public class DerbyStickersWithPosition implements Stickers {
	
	private final String database;
	private final Stickers origin;

	public DerbyStickersWithPosition(Stickers origin, String database) {

		this.origin = origin;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogStickersWithPosition
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
	
	private final String iterate_position_query = "select id, x, y from stickerwithposition";
	@Override
	public Collection<Sticker> iterate() {
		Collection<Sticker> it = origin.iterate();
		
		Collection<Sticker> toRemove = new ArrayList<Sticker>();
		Collection<Sticker> toAdd = new ArrayList<Sticker>();
		
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_position_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting position in the sticker with position
			while(rs.next()) {
				for (Sticker stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						System.out.println("Found " + stk.id() + " in stickerswithposition");
						toRemove.add(stk);
						toAdd.add(new DerbyStickerWithPosition(stk, new Point(rs.getInt(2), rs.getInt(3)), database));
					}
				}
			}
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting DerbyStickersWithPosition.iterate
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
