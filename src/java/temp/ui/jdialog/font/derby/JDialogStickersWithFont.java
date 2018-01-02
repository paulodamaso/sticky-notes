package temp.ui.jdialog.font.derby;

import java.awt.Font;
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
 * <p> Get the color of each {@link Note} from a derby database, in table 'stickerwithfont'
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickersWithFont implements JDialogStickers {

	private final String database;
	private final JDialogStickers origin;
	
	public JDialogStickersWithFont(JDialogStickers stickers, String database) {

		this.origin = stickers;
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for Jdialogsrtickerswithfont
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

	private final String iterate_font_query = "select id, name, style, size from stickerwithfont";
	@Override
	public Collection<JDialogSticker> iterate() {

		Collection<JDialogSticker> it = origin.notes();

		Connection conn = null;
		try {
			conn = connect();
			
			Collection<JDialogSticker> toRemove = new ArrayList<JDialogSticker>();
			Collection<JDialogSticker> toAdd = new ArrayList<JDialogSticker>();
			
			//tasks with font
			PreparedStatement ps = conn.prepareStatement(iterate_font_query);
			ResultSet rs = ps.executeQuery();
			
			//iterate in all tasks, setting font in the task with font
			while(rs.next()) {
				for (JDialogSticker stk : it) {
					
					int id = rs.getInt(1);
					if (id == stk.id()) {

						toRemove.add(stk);
						toAdd.add(new JDialogStickerWithFont(stk, new Font(rs.getString(2), rs.getInt(3), rs.getInt(4)), database));
					}
				}
			}
			
			it.removeAll(toRemove);
			it.addAll(toAdd);

		}catch (Exception e) {
			/* @todo #12 implement better exception handling when getting Iterable<JDialogStickerWithFont>.
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
		return it;
	}
	
	@Override
	public void print() {
		for (JDialogSticker jdsk : notes()){
			jdsk.print();
		}
	}

	@Override
	public Stickers stickers() {
		return origin.envelopes();
	}


}
