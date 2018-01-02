package temp.ui.jdialog.color.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import main.note.Note;
import main.note.Stickers;
import temp.ui.jdialog.JDialogSticker;
import temp.ui.jdialog.JDialogStickers;
import temp.ui.jdialog.SimpleJDialogStickers;

/**
 * <p> Get the color of each {@link Note} from a derby database, in table 'stickerwithcolor'
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickersWithColor implements JDialogStickers {

	private final String database;
	private final JDialogStickers origin;
	
	public JDialogStickersWithColor(SimpleJDialogStickers stickers, String database) {

		this.origin = stickers;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for Jdialogsrtickerswithcolor
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

	private final String iterate_color_query = "select id, red, green, blue from stickerwithcolor";
	@Override
	public Collection<JDialogSticker> iterate() {
//		//System.out.println("Iterating in JDialogStickersWithColor");
		Collection<JDialogSticker> it = origin.notes();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<JDialogSticker> toRemove = new ArrayList<JDialogSticker>();
			Collection<JDialogSticker> toAdd = new ArrayList<JDialogSticker>();
			
			//tasks with color
			PreparedStatement ps = conn.prepareStatement(iterate_color_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting color in the task with color
			while(rs.next()) {
				for (JDialogSticker stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {
//						//System.out.println("Found " + stk.id() + " in stickerswithcolor");
						toRemove.add(stk);
						toAdd.add(new JDialogStickerWithColor(stk, new Color(rs.getInt(2), rs.getInt(3), rs.getInt(4)), database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<JDialogStickerWithColor>.
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
//		//System.out.println("Iterated in JDialogStickersWithColor");
		return it;
	}
	
	public void print() {
//		//System.out.println("Printing in JDialogStickersWithColor");
		for (JDialogSticker jdsk : notes()){
			jdsk.print();
		}
//		//System.out.println("Printed in JDialogStickersWithColor");
	}

	@Override
	public Stickers stickers() {
//		//System.out.println("Reading stickers in JDialogStickersWithColor");
		return origin.envelopes();
	}


}
