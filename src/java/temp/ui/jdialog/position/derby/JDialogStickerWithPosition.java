package temp.ui.jdialog.position.derby;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.position.StickerWithPosition;
import main.note.Note;
import temp.ui.jdialog.JDialogSticker;

/**
 * <p> {@link JDialogSticker} with position implementation.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickerWithPosition implements JDialogSticker, StickerWithPosition {

	private final JDialogSticker origin;
	private final Point position;
	private final String database;
	
	public JDialogStickerWithPosition(JDialogSticker origin, Point position, String database) {
		this.origin = origin;
		this.position = position;
		this.database = database;
		
		origin.jdialog().setLocation(this.position);
		
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
	public void print() {
		//System.out.println("Printing jdialogstickerwith position: " + position);
		origin.print();
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
	public JDialogStickerWithPosition persist(Note sticker) {
		//System.out.println("Persisting JDialoStickerWithPosition " + id());
		//delegating sticker saving behavior to origin, persisting position only
		origin.persist(sticker);
		persistPosition();
		
		return new JDialogStickerWithPosition(origin, position(), database);
	}

	private final String insert_position_query = "insert into stickerwithposition (id, x, y) values ( ?, ?, ?)";
	private final String update_position_query = "update stickerwithposition set x = ?, y = ? where id = ?";
	private Point persistPosition () {
		Connection conn = null;
		try {
			Point position = jdialog().getLocation();
			
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
			/* @todo #12 implement better exception handling when saving JDialogStickerWithPosition
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving JDialogStickerWithPosition.
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
			/* @todo #12 implement better exception handling when retrieving position
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving position
				 * 
				 */
				e.printStackTrace();
			}
		}
		return position;
	}

	@Override
	public JDialog jdialog() {
		return origin.jdialog();
	}

	@Override
	public JTextArea txtDescription() {
		return origin.txtDescription();
	}

	@Override
	public JPopupMenu popup() {
		return origin.popup();
	}

	@Override
	public JMenuItem saveItem() {
		return origin.saveItem();
	}
}
