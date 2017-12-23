package main.ui.swing.sticker;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <p> Get the color of each {@link Sticker} from a derby database, in table 'stickerwithcolor'
 * 
 * @author paulodamaso
 *
 */
public class DerbyStickersWithColor implements Stickers {

	private String database;
	private Stickers origin;
	
	public DerbyStickersWithColor(Stickers stickers, String database) {

		this.origin = stickers;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver
			 * 
			 */
			e.printStackTrace();
		}		
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	@Override
	public void print() {
		for (Sticker stk : iterate()) {
			stk.print();
		}
	}

	@Override
	public Sticker add(String task) {
		return origin.add(task);
	}

	private final String iterate_color_query = "select id, red, green, blue from taskwithcolor";
	@Override
	public Iterable<Sticker> iterate() {
		Iterable<Sticker> it = origin.iterate();
		Connection conn = null;
		try {
			conn = connect();
			
			//tasks with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				for (Sticker stk : it) {
					int id = rs.getInt(1);
					if (id == stk.task().id()) {
						stk = new DerbyStickerWithColor(stk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4)), database);
					}
				}
				
			}

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<DerbyTaskswithcolor>.
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
