package main;

/**
 * <p> A list of {@link Task} printed in console output.
 * 
 * @author paulodamaso
 *
 */
public final class ConsoleTaskList implements Printable, Tasks {
	
	private final Tasks tasks;
	private final String title; 
	
	public ConsoleTaskList(Tasks tasks, String title) {
		this.tasks = tasks;
		this.title = title;
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate();
	}

	@Override
	public Tasks add(Task task) {
		return new ConsoleTaskList(tasks, title).add(task);
	}


	@Override
	public void print() {
		int count = 0;
		for(Task task : iterate()) {
			if (count == 0) {
				System.out.println(title);
				System.out.println("==============================================");
			}
			
			task.print();
			count++;
		}
		if (count != 0) {
			System.out.println("==============================================");
			System.out.println(count + " tarefas no total");
		} else {
			System.out.println("Lista vazia");
		}
		
	}

}
