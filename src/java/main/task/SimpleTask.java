package main.task;

public class SimpleTask implements Task {
	
	private final int id;
	private final String description;

	public SimpleTask(int id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public int id() {
		return this.id;
	}

	@Override
	public String description() {
		return this.description;
	}

}
