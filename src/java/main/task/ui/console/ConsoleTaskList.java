package main.task.ui.console;

import main.task.Task;
import main.task.Tasks;
import temp.ui.Printable;

/**
 * <p> A list of {@link Task} printed in console output.
 * 
 * @author paulodamaso
 *
 */
/* 
 * @todo #6 still smelly, it's a bridge in disguise: ConsoleTasks only handle ConsoleTask
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
	public Task add(String description) {
		return tasks.add(description);
	}


	@Override
	public void print() {
		int count = 0;
		for(Task task : iterate()) {
			if (count == 0) {
				//System.out.println(title);
				//System.out.println("==============================================");
			}
			ConsoleTask tsk = new ConsoleTask(task);
			tsk.print();
			count++;
		}
		if (count != 0) {
			//System.out.println("==============================================");
			//System.out.println(count + " tarefas no total");
		} else {
			//System.out.println("Lista vazia");
		}
		
	}

}
