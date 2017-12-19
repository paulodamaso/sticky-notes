package main;

import java.util.Arrays;

import main.ui.swing.PostItTask;
import main.ui.swing.PostItTasks;
import main.ui.swing.SystemTrayApplication;
import persistence.DerbyTasks;

public class Main {

	public static void main(String[] args) throws Exception{
	
		/*
		 * @todo #2 essa instanciação da lista de tarefas não está legal
		 *  melhor seria a utilização de uma interface aqui 
		 * 
		 */

		/* @todo #6 implement a complete application, with taskbar icon and persisting postit tasks
		 * 
		 */
//		PostItTasks tarefas = new PostItTasks(
//				new MemoryTasks(
//						Arrays.asList(
//								new PostItTask(new SimpleTask(1, "Fazer a lição de memória")),
//								new PostItTask(new SimpleTask(2,  "Limpar a lancheira de memória")),
//								new PostItTask(new SimpleTask(3,  "Guardar os tênis de memória")),
//								new PostItTask(new SimpleTask(4,  "Juntar roupa do banheiro após o banho de memória")),
//								new PostItTask(new SimpleTask(5,  "Arrumar a mesa de memória")),
//								new PostItTask(new SimpleTask(6,  "Assistir TV :D de mem[oria"))								)
//						));
//		
//		
//		tarefas.print();
//		
//		PostItTasks tarefasEmBanco = new PostItTasks(new DerbyTasks("resources/donkey-tasks"));
//		
//		tarefasEmBanco.print();
		
//		SystemTrayApplication app = new SystemTrayApplication(new PostItTasks(new DerbyTasks("resources/donkey-tasks")););
//		app.init();
		
		SystemTrayApplication app = new SystemTrayApplication(new PostItTasks(
				new MemoryTasks(
				Arrays.asList(
						new PostItTask(new SimpleTask(1, "Fazer a lição de memória")),
						new PostItTask(new SimpleTask(2,  "Limpar a lancheira de memória")),
						new PostItTask(new SimpleTask(3,  "Guardar os tênis de memória")),
						new PostItTask(new SimpleTask(4,  "Juntar roupa do banheiro após o banho de memória")),
						new PostItTask(new SimpleTask(5,  "Arrumar a mesa de memória")),
						new PostItTask(new SimpleTask(6,  "Assistir TV :D de mem[oria"))
						)
				)
			)
		);
		app.init();
		
		
		// TODO Auto-generated method stub

	}

}
