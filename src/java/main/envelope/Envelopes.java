package main.envelope;

import java.util.Collection;

import main.note.Note;

/**
 * <p> Collection of {@link Envelope} enveloping {@link Note}.
 * 
 * @author paulodamaso
 *
 */
public interface Envelopes {
	
	public Collection<Envelope> iterate();
	
	public Envelope add(String note);
	
//	public Envelope add(String text);
}
