package temp.ui.jdialog.size.derby;

import java.awt.Dimension;
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

/**
 * <p> Get the size of each {@link Note} from a derby database, in table 'stickerwithsize'
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickersWithSize implements JDialogStickers {

	private final String database;
	private final JDialogStickers origin;
	
	public JDialogStickersWithSize(JDialogStickers stickers, String database) {

		this.origin = stickers;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogStickersWithSize
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

	private final String iterate_position_query = "select id, width, height from stickerwithsize";
	@Override
	public Collection<JDialogSticker> iterate() {
		//System.out.println("Iterating in JDialogStickersWithPosition");
		Collection<JDialogSticker> it = origin.notes();
		
		Collection<JDialogSticker> toRemove = new ArrayList<JDialogSticker>();
		Collection<JDialogSticker> toAdd = new ArrayList<JDialogSticker>();
		
		
		Connection conn = null;
		try {
			conn = connect();
			
			PreparedStatement ps = conn.prepareStatement(iterate_position_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all stickers, setting size in the sticker with size
			while(rs.next()) {
				for (JDialogSticker stk : it) {
					int id = rs.getInt(1);
					if (id == stk.id()) {
						//System.out.println("Found " + stk.id() + " in stickerswithsize");
						toRemove.add(stk);
						toAdd.add(new JDialogStickerWithSize(stk, new Dimension(rs.getInt(2), rs.getInt(3)), database));
					}
				}
			}
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<JDialogStickersWithSize>.
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
		//System.out.println("Iterated in JDialogStickersWithSize");
		return it;
	}
	
	public void print() {
		//System.out.println("Printing in JDialogStickersWithSize");
		for (JDialogSticker jdsk : notes()){
			jdsk.print();
		}
		//System.out.println("Printed in JDialogStickersWithSize");
	}

	@Override
	public Stickers stickers() {
		//System.out.println("Reading stickers in JDialogStickersWithSize");
		return origin.envelopes();
	}

}
