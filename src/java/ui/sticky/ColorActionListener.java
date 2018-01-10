package ui.sticky;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.SimpleEnvelopeWithColor;
import ui.PrintMedia;

public final class ColorActionListener implements ActionListener {
	
	private final Application application;
	private final Envelope envelope;
	
	public ColorActionListener(Envelope envelope, Application application) {
		this.application = application;
		this.envelope = envelope;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		//show a colorchooser
		Color newColor = JColorChooser.showDialog(null,
                "Choose Color",
                new Color(251,247,174)
                );
		if (newColor != null) {
//			txtArea().setBackground(newColor);
			
			Envelope  env = application.colorFactory().create(new SimpleEnvelopeWithColor(envelope, newColor));
			//create new sticky
			PrintMedia pm = application.mediaFactory().create(env);
			env.print(pm);
			
			//dispose of this sticky
//			SimpleJDialogSticky.this.jdialog.dispose();
			
		}
	}


}
