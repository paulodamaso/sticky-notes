package main.envelope;

/**
 * <p> Factory for creating {@link Envelope} and {@link Envelopes}. 
 * 
 * Implementing this interface allows to access {@link Envelope} and {@link Envelopes}
 * classes persistence information.     
 * 
 * @author paulodamaso
 *
 * @param <T> 
 * @param <U>
 */
public interface EnvelopeFactory<T extends Envelope, U extends Envelopes> {

	public T create (T envelope);
	public U createEnvelopes (Envelopes envelopes);
	
	/**
	 * <p> Checks the repository status. 
	 * 
	 * @return
	 */
	public boolean check();
	
	/**
	 * <p> Initialize the repository.
	 */
	public void init();

}
