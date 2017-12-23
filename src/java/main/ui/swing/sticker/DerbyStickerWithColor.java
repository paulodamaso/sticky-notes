package main.ui.swing.sticker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.Task;

public class DerbyStickerWithColor implements StickerWithColor, PersistentSticker {
	
	private Sticker origin;
	private String database;
	private Color color;

	public DerbyStickerWithColor(Sticker origin, Color color, String database) {
		this.origin = origin;
		this.color = color;
		//adding color to the textarea
        description().setBackground(color);
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Cor...");
        
        //adding action to color menu
        System.out.println("Adding menu");
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
        
        //adding action to text area
        description().addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
            	persist(DerbyStickerWithColor.this);
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
	public DerbyStickerWithColor persist(Sticker sticker) {
		Connection conn = null;
		try {
			try {
				/* @todo #12 typecasting for save
				 *  again being a criminal saving with typecasting
				 */
				((PersistentSticker)origin).persist(origin);
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("You can't save this sticker!");
			}

			//saving origin sticker first
			
			
			Color color = description().getBackground();
			
			//saving color info
			PreparedStatement ps = null;
			
			if (color() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_color_query);
				ps.setInt(1, color.getRed());
				ps.setInt(2, color.getGreen());
				ps.setInt(3, color.getBlue());
				ps.setInt(4, task().id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_color_query);
				ps.setInt(1, task().id());
				ps.setInt(2, description().getBackground().getRed());
				ps.setInt(3, description().getBackground().getGreen());
				ps.setInt(4, description().getBackground().getBlue());
			}

			ps.executeUpdate();
			
			return this;

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
		return null;
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

	@Override
	public JTextArea description() {
		return origin.description();
	}

	@Override
	public JPopupMenu popUpMenu() {
		return origin.popUpMenu();
	}

	@Override
	public Task task() {
		return origin.task();
	}

	@Override
	public void print() {
		origin.print();
	}

}
