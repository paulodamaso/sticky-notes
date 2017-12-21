package main.ui;

import main.Task;
import main.Tasks;

/**
 * <p> Top-level interface for {@link TaskWithColor} 
 * @author paulodamaso
 *
 * @param <T>
 */
public interface TasksWithColor<T extends TaskWithColor> extends Tasks<Task> {
	
	public T add(T task);
	
	@Override
	public T add(String description);

}
