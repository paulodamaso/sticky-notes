package main;

import java.util.Arrays;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
	
		/*
		 * @todo #2 essa instanciação da lista de tarefas não está legal
		 *  melhor seria a utilização de uma interface aqui 
		 * 
		 */
		/*
		 *  @todo #4 implement an swing task list 
		 */
		PostItTasks tarefas = new PostItTasks(
				new MemoryTasks(
						Arrays.asList(
								new PostItTask(new SimpleTask(1, new Date(), "Fazer a lição")),
								new PostItTask(new SimpleTask(2, new Date(), "Limpar a lancheira")),
								new PostItTask(new SimpleTask(3, new Date(), "Guardar os tênis")),
								new PostItTask(new SimpleTask(4, new Date(), "Juntar roupa do banheiro após o banho")),
								new PostItTask(new SimpleTask(5, new Date(), "Arrumar a mesa")),
								new PostItTask(new SimpleTask(6, new Date(), "Assistir TV :D"))
								)
						));
		
		
		tarefas.print();
		
		// TODO Auto-generated method stub

	}

}
