package main.ui.swing.sticker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

public class DerbyStickerWithColor implements StickerWithColor {
	
	private Sticker origin;
	private String database;

	public DerbyStickerWithColor(Sticker origin, Color color, String database) {
		this.origin = origin;
		//adding color to the textarea
        description().setBackground(color);
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Cor...");
        
        //adding action to color menu
        colorMenu.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {

				//show a colorchooser
				Color newColor = JColorChooser.showDialog(null,
	                     "Choose Color",
	                     description().getBackground());
				
				description().setBackground(newColor);
				
			}
		});
        
        popUpMenu().add(colorMenu);

		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver
			 * 
			 */
			e.printStackTrace();
		}	
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";");
	}
	
	private final String insert_color_query = "insert into taskwithcolor (id, red, green, blue) values ( ?, ?, ?  ?)";
	private final String update_color_query = "update taskwithcolor set red = ?, green = ?, blue = ? where id = ?";
	
	@Override	
	public DerbyStickerWithColor persist(DerbyStickerWithColor sticker) {
		Connection conn = null;
		try {
			//saving origin sticker first
			origin.save();
			
			//saving color info
			PreparedStatement ps = null;
			
			if (color() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_color_query);
				ps.setInt(1, color().getRed());
				ps.setInt(2, color().getGreen());
				ps.setInt(3, color().getBlue());
				ps.setInt(4, task().id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_color_query);
				ps.setInt(1, task().id());
				ps.setInt(2, color().getRed());
				ps.setInt(3, color().getGreen());
				ps.setInt(4, color().getBlue());
			}

			ps.executeUpdate();

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving derbytaskwithcolor
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closin connection after saving derbytaskwithcolor.
				 * 
				 */
				e.printStackTrace();
			}
		}
	}
	
	private final String color_query = "select  red, green, blue from taskwithcolor where id = ?";
	@Override
	public Color color() {
		Connection conn = null;
		Color color = null;
		try {
			/* @todo #10 criminal bad design  
			 *  the id should have be the sticker.id, not task.id 
			 */
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(color_query);
			ps.setInt(1, task().id());

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
}
