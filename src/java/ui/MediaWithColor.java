package ui;

import java.awt.Color;

import main.envelope.Envelope;

/**
 * <p> {@link PrintMedia} to deal with color information for {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaWithColor extends PrintMedia {

	public Color color(Color color);

}
