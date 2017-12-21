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

	private final String color_query = "select  red, green, blue from taskwithcolor where id = ?";
	@Override
	public Color color(Sticker sticker) {
		Connection conn = null;
		Color color = null;
		try {
			/* @todo #10 criminal bad design  
			 *  the id should have bt the sticker.id, not task.id 
			 */
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(color_query);
			ps.setInt(1, sticker.task().id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				color =  new Color(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving color
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving color
				 * 
				 */
				e.printStackTrace();
			}
		}
		return color;
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

}
