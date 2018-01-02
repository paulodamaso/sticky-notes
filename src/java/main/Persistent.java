package main;

public interface Persistent<T> {

	public T persist(T persistent);
}
