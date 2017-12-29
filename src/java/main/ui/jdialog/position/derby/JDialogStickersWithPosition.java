package main.ui.jdialog.position.derby;

import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.sticker.Sticker;
import main.sticker.Stickers;
import main.ui.jdialog.JDialogSticker;
import main.ui.jdialog.JDialogStickers;

/**
 * <p> Get the position of each {@link Sticker} from a derby database, in table 'stickerwithposition'
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickersWithPosition implements JDialogStickers {

	private final String database;
	private final JDialogStickers origin;
	
	public JDialogStickersWithPosition(JDialogStickers stickers, String database) {

		this.origin = stickers;
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
	public JDialogSticker add(String text) {
		return origin.add(text);
	}

	private final String iterate_position_query = "select id, x, y from stickerwithposition";
	@Override
	public Collection<JDialogSticker> iterate() {
		//System.out.println("Iterating in JDialogStickersWithPosition");
		Collection<JDialogSticker> it = origin.iterate();
		
		Collection<JDialogSticker> toRemove = new ArrayList<JDialogSticker>();
		Collection<JDialogSticker> toAdd = new ArrayList<JDialogSticker>();
		
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_position_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting position in the task with position
			while(rs.next()) {
				for (JDialogSticker stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						//System.out.println("Found " + stk.id() + " in stickerswithposition");
						toRemove.add(stk);
						toAdd.add(new JDialogStickerWithPosition(stk, new Point(rs.getInt(2), rs.getInt(3)), database));
					}
				}
			}
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<JDialogStickersWithPosition>.
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
		//System.out.println("Iterated in JDialogStickersWithPosition");
		return it;
	}
	
	public void print() {
		//System.out.println("Printing in JDialogStickersWithPosition");
		for (JDialogSticker jdsk : iterate()){
			jdsk.print();
		}
		//System.out.println("Printed in JDialogStickersWithPosition");
	}

	@Override
	public Stickers stickers() {
		//System.out.println("Reading stickers in JDialogStickersWithPosition");
		return origin.stickers();
	}

}
