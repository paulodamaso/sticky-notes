package main.sticker.position.derby;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.sticker.Sticker;
import main.sticker.position.StickerWithPosition;

public final class DerbyStickerWithPosition implements StickerWithPosition {

	private final Sticker origin;
	private final Point position;
	private final String database;
	
	public DerbyStickerWithPosition(Sticker origin, Point position, String database) {
		this.origin = origin;
		this.position = position;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogStickerWithPosition
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
	public DerbyStickerWithPosition persist(Sticker sticker) {
		System.out.println("Persisting JDialoStickerWithPosition " + id());
		//delegating sticker saving behavior to origin, persisting position only
		origin.persist(sticker);
		
		return new DerbyStickerWithPosition(origin, persistPosition(), database);
	}

	private final String insert_position_query = "insert into stickerwithposition (id, x, y) values ( ?, ?, ?)";
	private final String update_position_query = "update stickerwithposition set x = ?, y = ? where id = ?";
	private Point persistPosition () {
		Connection conn = null;
		try {
			
			//saving position info
			PreparedStatement ps = null;
			
			if (position() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_position_query);
				ps.setInt(1, position.x);
				ps.setInt(2, position.y);
				ps.setInt(3, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_position_query);
				ps.setInt(1, id());
				ps.setInt(2, position.x);
				ps.setInt(3, position.y);
			}

			ps.executeUpdate();
			
			return position;

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving DerbyStickerWithPosition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving DerbyStickerWithPosition.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String position_query = "select x, y from stickerwithposition where id = ?";
	@Override
	public Point position() {
		Connection conn = null;
		Point position = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(position_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				position =  new Point(rs.getInt(1), rs.getInt(2));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving position in DerbyStickerWithPosition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving position in DerbyStickerWithPosition
				 * 
				 */
				e.printStackTrace();
			}
		}
		return position;
	}

}
