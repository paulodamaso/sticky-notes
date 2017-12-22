package main.ui.swing.sticker;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * <p> Get the color of each {@link Sticker} from a derby database, in table 'stickerwithcolor'
 * 
 * @author paulodamaso
 *
 */
public class DerbyStickersWithColor extends StickersWithColor {

	private String database;
	private Stickers origin;
	
	public DerbyStickersWithColor(Stickers stickers, String database) {
		super(stickers);
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

	@Override
	public Iterable<Sticker> iterate() {
		ArrayList<Sticker> arr = new ArrayList<Sticker>();
		for (Sticker sticker : origin.iterate()) {
			//just to avoid two database queries
			Color color = color(sticker);

			if (color != null) 
				sticker = new DerbyStickerWithColor(sticker, color);

			arr.add(sticker);
		}
		return arr;
	}

}
