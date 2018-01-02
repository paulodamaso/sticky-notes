package temp.ui.jdialog.color.derby;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.envelope.color.EnvelopeWithColor;
import main.note.Note;
import temp.ui.jdialog.JDialogSticker;
import temp.ui.jdialog.persistence.StickerSaveActionListener;

/**
 * <p> {@link JDialogSticker} with color implementation.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickerWithColor implements JDialogSticker, EnvelopeWithColor  {
	
	private final JDialogSticker origin;
	private final String database;
	private final Color color;

	public JDialogStickerWithColor(JDialogSticker origin, Color color, String database) {
		this.origin = origin;
		this.color = color;
		this.database = database;
		
		//adding color to the textarea
        txtDescription().setBackground(this.color);
        
        /*
         * @todo #24 very disgusting way of propagating persist behavior called from menuitem in JDialogStickerWithColor
         *  had to do this way because i don't want to save the sticker object in each decoration of jdialogsticker
         */
        //replacing actionlistener from save menu item
        for (ActionListener act : saveItem().getActionListeners()) {
        	if (act instanceof StickerSaveActionListener) {
        		saveItem().removeActionListener(act);
        	}
        	saveItem().addActionListener(new StickerSaveActionListener(this));
        }

		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for JDialogStickerWithColor
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}
	

	@Override	
	public JDialogStickerWithColor persist(Note sticker) {
	
		//delegating sticker saving behavior to origin, persisting colors only
		origin.persist(sticker);
		persistColor();
		
		return new JDialogStickerWithColor(origin, color(), database);

	}

	private final String insert_color_query = "insert into stickerwithcolor (id, red, green, blue) values ( ?, ?, ?, ?)";
	private final String update_color_query = "update stickerwithcolor set red = ?, green = ?, blue = ? where id = ?";
	private Color persistColor() {
		Connection conn = null;
		try {
			Color color = txtDescription().getBackground();
			
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
		//System.out.println("Printing jdialogstickerwith color: " + color);
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
	public JDialog jdialog() {
		return  origin.jdialog();
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
