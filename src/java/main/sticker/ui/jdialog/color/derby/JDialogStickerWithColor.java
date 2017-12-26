package main.sticker.ui.jdialog.color.derby;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.sticker.Sticker;
import main.sticker.color.StickerWithColor;
import main.sticker.ui.jdialog.JDialogSticker;
import main.sticker.ui.jdialog.persistence.StickerSaveActionListener;

public class JDialogStickerWithColor implements JDialogSticker, StickerWithColor  {
	
	private JDialogSticker origin;
	private String database;
	private Color color;

	public JDialogStickerWithColor(JDialogSticker origin, Color color, String database) {
		this.origin = origin;
		this.color = color;
		this.database = database;
		
		//adding color to the textarea
        txtDescription().setBackground(this.color);
        
        //setting the popup menu to show save option
        JMenuItem saveMenu = new JMenuItem("Salvar com cor");
        saveMenu.addActionListener(new StickerSaveActionListener(this));
        popup().add(saveMenu);


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
	public JDialogStickerWithColor persist(Sticker sticker) {
		System.out.println("Persisting JDialoStickerWithColor " + id());
	
		//delegating sticker saving behavior to origin, persisting colors only
		origin.persist(sticker);
		persistColor();
		
		return new JDialogStickerWithColor(origin, color(), database);

	}

	private final String insert_color_query = "insert into taskwithcolor (id, red, green, blue) values ( ?, ?, ?, ?)";
	private final String update_color_query = "update taskwithcolor set red = ?, green = ?, blue = ? where id = ?";
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
	
	private final String color_query = "select  red, green, blue from taskwithcolor where id = ?";
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
		System.out.println("Printing jdialogstickerwith color: " + color);
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
}
