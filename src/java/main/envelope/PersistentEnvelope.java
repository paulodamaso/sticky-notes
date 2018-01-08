package main.envelope;

public interface PersistentEnvelope<T extends Envelope, U extends T> {

	public U save(T persistent);
}
