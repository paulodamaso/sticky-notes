package main.sticker.size.derby;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.sticker.Sticker;
import main.sticker.size.StickerWithSize;

public final class DerbyStickerWithSize implements StickerWithSize{
	
	private final Sticker origin;
	private final Dimension size;
	private final String database;

	public DerbyStickerWithSize(Sticker origin, Dimension size, String database) {
		this.origin = origin;
		this.size = size;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyStickerWithSize
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public DerbyStickerWithSize persist(Sticker sticker) {
		//delegating sticker saving behavior to origin, persisting size only
		origin.persist(sticker);
		
		return new DerbyStickerWithSize(origin, persistSize(), database);
	}

	private final String insert_position_query = "insert into stickerwithsize (id, width, height) values ( ?, ?, ?)";
	private final String update_position_query = "update stickerwithsize set width = ?, height = ? where id = ?";
	private Dimension persistSize () {
		Connection conn = null;
		try {
			
			//saving position info
			PreparedStatement ps = null;
			
			if (size() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_position_query);
				ps.setInt(1, size.width);
				ps.setInt(2, size.height);
				ps.setInt(3, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_position_query);
				ps.setInt(1, id());
				ps.setInt(2, size.width);
				ps.setInt(3, size.height);
			}

			ps.executeUpdate();
			
			return size;

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving DerbyStickerWithSize
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving DerbyStickerWithSize.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String size_query = "select width, height from stickerwithsize where id = ?";
	@Override
	public Dimension size() {
		Connection conn = null;
		Dimension size = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(size_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				size =  new Dimension(rs.getInt(1), rs.getInt(2));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving size from DerbyStickerWithSize
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving size from DerbyStickerWithSize
				 * 
				 */
				e.printStackTrace();
			}
		}
		return size;
	}

}
