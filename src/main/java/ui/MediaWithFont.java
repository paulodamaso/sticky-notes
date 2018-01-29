package ui;

import java.awt.Font;

import main.envelope.Envelope;

/**
 * <p> {@link PrintMedia} to deal with font information for {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaWithFont extends PrintMedia {

	public Font font (Font font);
}
