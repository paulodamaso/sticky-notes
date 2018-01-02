package temp.ui.jdialog.size.derby;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.note.Note;
import temp.envelope.size.StickerWithSize;
import temp.ui.jdialog.JDialogSticker;

/**
 * <p> {@link JDialogSticker} with size implementation.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickerWithSize implements JDialogSticker, StickerWithSize {
	
	private final JDialogSticker origin;
	private final Dimension size;
	private final String database;

	public JDialogStickerWithSize(JDialogSticker origin, Dimension size, String database) {
		this.origin = origin;
		this.size = size;
		this.database = database;
		
		origin.jdialog().setSize(this.size);
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogStickerWithSize
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
		//System.out.println("Printing jdialogstickerwith size: " + size);
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
	public JDialogStickerWithSize persist(Note sticker) {
		//System.out.println("Persisting JDialoStickerWithSize " + id());
		//delegating sticker saving behavior to origin, persisting size only
		origin.persist(sticker);
		persistSize();
		
		return new JDialogStickerWithSize(origin, size(), database);
	}

	private final String insert_position_query = "insert into stickerwithsize (id, width, height) values ( ?, ?, ?)";
	private final String update_position_query = "update stickerwithsize set width = ?, height = ? where id = ?";
	private Dimension persistSize () {
		Connection conn = null;
		try {
			Dimension size = jdialog().getSize();
			
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
			/* @todo #12 implement better exception handling when saving JDialogStickerWithsize
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving JDialogStickerWithSize.
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
			/* @todo #12 implement better exception handling when retrieving size
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving size
				 * 
				 */
				e.printStackTrace();
			}
		}
		return size;
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
