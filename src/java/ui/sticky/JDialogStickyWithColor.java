package ui.sticky;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.EnvelopeWithColor;
import ui.MediaWithColor;
import ui.PrintMedia;

/**
 * <p> {@link JDialogSticky} with color information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithColor implements JDialogSticky , MediaWithColor{
	
	private final EnvelopeWithColor envelopeWithColor;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithColor without type casting
	 */
	public JDialogStickyWithColor(EnvelopeWithColor envelopeWithColor, PrintMedia media) {
		this.envelopeWithColor = envelopeWithColor;
		this.media = (JDialogSticky)media;
		
		this.media.jDialog().setTitle(this.envelopeWithColor.getClass().toString());
		
//		this.colorMenuItem().addActionListener(new ColorActionListener(envelopeWithColor, application()));
		
		this.media.txtArea().setBackground(this.envelopeWithColor.color());
		
//        //adding listener to save via menu
//        saveItem().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				application.add(JDialogSimpleSticky.this.envelope);
//				JDialogStickyWithColor.this.envelopeWithColor.color(envelopeWithColor.color());
//				save();
//			}
//		});

	}

	@Override
	public JDialog jDialog() {
		return media.jDialog();
	}

	@Override
	public JTextArea txtArea() {
		return media.txtArea();
	}

	@Override
	public Application application() {
		return media.application();
	}

	@Override
	public Color color(Color color) {
		return this.envelopeWithColor.color();
	}
	
	@Override
	public Envelope envelope() {
		return this.envelopeWithColor;
	}
	
	@Override
	public JMenuItem saveItem() {
		return this.media.saveItem();
	}
	
	@Override
	public void save() {
		System.out.println("Saved " + this.getClass());
	}
}
