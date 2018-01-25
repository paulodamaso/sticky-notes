package ui.sticky;

import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.envelope.position.EnvelopeWithPosition;
import ui.MediaWithPosition;
import ui.PrintMedia;

/**
 * <p> {@link JDialogSticky} with position information.
 * 
 * @author paulodamaso
 *
 */
public final class JDialogStickyWithPosition implements JDialogSticky , MediaWithPosition{
	
	private final EnvelopeWithPosition envelopeWithPosition;
	private final JDialogSticky media;

	/*
	 * @todo #22 create a JDialogStickyWithPosition without type casting
	 */
	public JDialogStickyWithPosition(EnvelopeWithPosition envelopeWithPosition, PrintMedia media) {
		this.envelopeWithPosition = envelopeWithPosition;
		this.media = (JDialogSticky)media;
		
		jDialog().setLocation(this.envelopeWithPosition.position());
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
	public Point position(Point position) {
		return envelopeWithPosition.position();
	}

	@Override
	public Application application() {
		return this.media.application();
	}
	
	@Override
	public Envelope envelope() {
		return this.envelopeWithPosition;
	}

	@Override
	public JMenuItem saveItem() {
		return this.media.saveItem();
	}
	
	@Override
	public JMenuItem deleteItem() {
		return this.media.deleteItem();
	}
	
	@Override
	public void save() {
		System.out.println("Saved " + this.getClass());
	}

}
