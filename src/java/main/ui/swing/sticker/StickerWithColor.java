package main.ui.swing.sticker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import main.persistence.PersistentTask;

public class StickerWithColor extends Sticker{
	
//	private Sticker origin;
//	private Color color;

	public StickerWithColor(Sticker origin, Color color) {
		super(origin.task);
	
		//adding color to the textarea
        txtDescription().setBackground(color);
        
        //setting the popup menu to show color select option
        JMenuItem colorMenu = new JMenuItem("Cor...");
        
        //adding action to color menu
        colorMenu.addActionListener(new ActionListener() {
			@Override			
			public void actionPerformed(ActionEvent e) {

				//show a colorchooser
				Color newColor = JColorChooser.showDialog(null,
	                     "Choose Color",
	                     txtDescription().getBackground());
				
				txtDescription().setBackground(newColor);
				
			}
		});
        
        
        popUpMenu().add(colorMenu);
	}

	public void save() {
		//lousy sticker, does not have position, color, nothing; should just save task info
		//this.task.save ?
		//you're a criminal!
		try {
			((PersistentTask)task).save();
		} catch (Exception e) {
			System.out.println("You can't save this task!");
		}
	}
}
