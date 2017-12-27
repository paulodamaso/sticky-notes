package main.sticker.ui.jdialog.font.derby;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.sticker.font.StickerWithFont;
import main.sticker.ui.jdialog.JDialogSticker;
import main.sticker.ui.jdialog.persistence.StickerSaveActionListener;

/**
 * <p> {@link JDialogSticker} with font implementation.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickerWithFont implements JDialogSticker, StickerWithFont  {
	
	private final JDialogSticker origin;
	private final String database;
	private final Font font;

	public JDialogStickerWithFont(JDialogSticker origin, Font font, String database) {
		this.origin = origin;
		this.font = font;
		this.database = database;
		
		//setting the textarea font
        txtDescription().setFont(this.font);
        
        /*
         * @todo #27 very disgusting way of propagating persist behavior called from menuitem in JDialogStickerWithFont
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
	public JDialogStickerWithFont persist(Sticker sticker) {
		System.out.println("Persisting JDialoStickerWithFont " + id());
	
		//delegating sticker saving behavior to origin, persisting font info only
		origin.persist(sticker);
		persistFont();
		
		return new JDialogStickerWithFont(origin, font(), database);

	}

	private final String insert_font_query = "insert into stickerwithfont (id, name, style, size) values ( ?, ?, ?, ?)";
	private final String update_font_query = "update stickerwithfont set name = ?, style = ?, size = ? where id = ?";
	private Font persistFont() {
		Connection conn = null;
		try {
			Font color = txtDescription().getFont();
			
			//saving color info
			PreparedStatement ps = null;
			
			if (font() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_font_query);
				ps.setString(1, font.getFamily());
				ps.setInt(2, font.getStyle());
				ps.setInt(3, font.getSize());
				ps.setInt(4, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_font_query);
				ps.setInt(1, id());
				ps.setString(2, font.getFamily());
				ps.setInt(3, font.getStyle());
				ps.setInt(4, font.getSize());
			}
	
			ps.executeUpdate();
			
			return color;
	
		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving JDialogStickerWithFont
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving JDialogStickerWithFont.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String font_query = "select name, style, size from stickerwithfont where id = ?";
	@Override
	public Font font() {
		Connection conn = null;
		Font font = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(font_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				font =  new Font(rs.getString(1), rs.getInt(2), rs.getInt(3));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving font
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving font
				 * 
				 */
				e.printStackTrace();
			}
		}
		return font;
	}

	@Override
	public void print() {
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
