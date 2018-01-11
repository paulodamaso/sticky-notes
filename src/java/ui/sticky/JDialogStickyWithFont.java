package ui.sticky;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.envelope.font.EnvelopeWithFont;
import ui.MediaWithFont;
import ui.PrintMedia;

/**
 * <p> {@link JDialogSticky} with font information.
 * 
 * @author paulodamaso
 *
 */
public class JDialogStickyWithFont implements JDialogSticky, MediaWithFont {

	private final EnvelopeWithFont envelopeWithFont;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithFont without type casting
	 */
	public JDialogStickyWithFont(EnvelopeWithFont envelopeWithFont, PrintMedia media) {
		this.envelopeWithFont = envelopeWithFont;
		this.media = (JDialogSticky)media;
		
		this.media.jDialog().setTitle(this.envelopeWithFont.getClass().toString());
		
		this.txtArea().setFont((this.envelopeWithFont.font()));
//		saveItem().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
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
	public Font font(Font font) {
		return envelopeWithFont.font();
	}

	@Override
	public Application application() {
		return media.application();
	}
	
	@Override
	public Envelope envelope() {
		return this.envelopeWithFont;
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
