package ui;

import java.awt.Dimension;

import main.envelope.Envelope;

/**
 * <p> {@link PrintMedia} to deal with size information for {@link Envelope}.
 * 
 * @author paulodamaso
 *
 */
public interface MediaWithSize extends PrintMedia {

	public Dimension size(Dimension size);
}
