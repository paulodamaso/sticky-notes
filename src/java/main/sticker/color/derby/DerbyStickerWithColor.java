package main.sticker.color.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;

/**
 * <p> {@link StickerWithColor} implementations with color data in derby database, in table 'stickerwithcolor'.
 * 
 * @author paulodamaso
 *
 */
public class DerbyStickerWithColor implements StickerWithColor {
	
	private final Sticker origin;
	private final String database;
	private final Color color;

	public DerbyStickerWithColor(Sticker origin, Color color,  String database) {
		this.origin = origin;
		this.database = database;
		this.color = color;
	}

	@Override
	public Sticker persist(Sticker sticker) {
		
		//delegating sticker saving behavior to origin, persisting colors only
		origin.persist(sticker);
		
		return new DerbyStickerWithColor(origin, persistColor(), database);
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

	private final String color_query = "select  red, green, blue from stickerwithcolor where id = ?";
	@Override
	public Color color() {
		Connection conn = null;
		Color color = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(color_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				color =  new Color(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving color from derby
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving color from derby
				 * 
				 */
				e.printStackTrace();
			}
		}
		return color;
	}
	
	private final String insert_color_query = "insert into stickerwithcolor (id, red, green, blue) values ( ?, ?, ?, ?)";
	private final String update_color_query = "update stickerwithcolor set red = ?, green = ?, blue = ? where id = ?";
	private Color persistColor() {
		Connection conn = null;
		try {
			Color color = this.color;
			
			//saving color info
			PreparedStatement ps = null;
			
			if (color() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_color_query);
				ps.setInt(1, color.getRed());
				ps.setInt(2, color.getGreen());
				ps.setInt(3, color.getBlue());
				ps.setInt(4, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_color_query);
				ps.setInt(1, id());
				ps.setInt(2, color.getRed());
				ps.setInt(3, color.getGreen());
				ps.setInt(4, color.getBlue());
			}
	
			ps.executeUpdate();
			
			return color;
	
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving JDialogStickerWithColor
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving JDialogStickerWithColor.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}

}
