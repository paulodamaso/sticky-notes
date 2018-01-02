package main.note.persistence;

public interface Persistent<T> {

	public T persist(T persistent);
}
