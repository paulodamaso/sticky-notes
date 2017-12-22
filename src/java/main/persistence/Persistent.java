package main.persistence;

/**
 * <p> Interface defining behavior for persistent objects 
 * 
 * @author paulodamaso
 *
 */
public interface Persistent<T> {

	public T  persist(T persistent);
}
