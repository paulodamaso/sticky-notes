package main;

public interface Tasks {
	
	public Iterable<Task> iterate();
	
	public Tasks add(Task task);
}
