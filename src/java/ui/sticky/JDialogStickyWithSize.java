package ui.sticky;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.envelope.size.EnvelopeWithSize;
import ui.MediaWithSize;
import ui.PrintMedia;

/**
 * <p> {@link JDialogSticky} with color information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithSize implements JDialogSticky , MediaWithSize{
	
	private final EnvelopeWithSize envelopeWithSize;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithSize without type casting
	 */
	public JDialogStickyWithSize(EnvelopeWithSize envelopeWithSize, PrintMedia media) {
		this.envelopeWithSize = envelopeWithSize;
		this.media = (JDialogSticky)media;
		
		jDialog().setSize((this.envelopeWithSize.size()));
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
	public Dimension size(Dimension size) {
		return envelopeWithSize.size();
	}

	@Override
	public Application application() {
		return media.application();
	}
	
	@Override
	public Envelope envelope() {
		return this.envelopeWithSize;
	}

	@Override
	public JMenuItem saveItem() {
		return this.media.saveItem();
	}
	
	@Override
	public void save() {
		System.out.println("Saved " + this.getClass());
	}
	
	@Override
	public JMenuItem deleteItem() {
		return this.media.deleteItem();
	}
	
 
}
