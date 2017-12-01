package main;

public final class ConsoleTaskList implements Printable, Tasks {
	
	private final Tasks tasks;
	
	public ConsoleTaskList(Tasks tasks) {
		this.tasks = tasks;
	}

	@Override
	public Iterable<Task> iterate() {
		return tasks.iterate();
	}

	@Override
	public Tasks add(Task task) {
		return tasks.add(task);
	}

	/*
	 * @TODO #0001 ainda não sei qual a melhor maneira de fazer essa impressão:
	 * - PrintableTask.print(), e chamando uma tarefa printable ConsolePrintableTask() (bridge)?
	 * - ConsoleTaskList.print(), imprimindo as tarefas de acordo com essa lógica aqui
	 */
	@Override
	public void print() {
		int count = 0;
		for(Task task : iterate()) {
			if (count == 0) {
				System.out.println("Lista de tarefas do pequeno asno");
				System.out.println("==============================================");
			}
			
			System.out.println(task.id() + " " + task.description() );
			count++;
		}
		if (count != 0) {
			System.out.println("==============================================");
			System.out.println(count + " tarefas no total");
		}
		
	}

}
