package main.envelope;

import java.util.Collection;

import main.note.Note;

/**
 * <p> Collection of {@link Envelope} enveloping {@link Note}.
 * 
 * @author paulodamaso
 *
 */
public interface Envelopes<T extends Envelope> {
	
	public Collection<Envelope> iterate();
	
	public Envelope add(Note note);
	
	public T add(T envelope);
}
