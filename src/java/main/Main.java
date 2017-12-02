package main;

import java.util.Arrays;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
	
		/*

		 * @TODO #2 essa instanciação da lista de tarefas não está legal
		 * melhor seria a utilização de uma interface aqui 
		 * 
		 */
		ConsoleTaskList tarefas = new ConsoleTaskList(new MemoryTasks(Arrays.asList(
				new BasicTask(1, new Date(), "Fazer a li��o"),
				new BasicTask(2, new Date(), "Limpar a lancheira"),
				new BasicTask(3, new Date(), "Guardar os t�nis"),
				new BasicTask(4, new Date(), "Juntar roupa do banheiro ap�s o banho"),
				new BasicTask(5, new Date(), "Arrumar a mesa"),
				new BasicTask(6, new Date(), "Assistir TV :D")
				)));
		
		tarefas.print();
		
		
		// TODO Auto-generated method stub

	}

}
