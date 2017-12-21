package main.ui.swing;

import main.Task;
import main.Tasks;
import main.persistence.PersistentTasks;
import main.persistence.derby.DerbyTask;
import main.ui.Printable;
import main.ui.PrintableTask;
import main.ui.PrintableTasks;

/**
 * <p> A interface for {@link StickerTask}
 * 
 * @author paulodamaso
 *
 */
public interface StickerTasks <T extends StickerTask> extends PrintableTasks<T> {
	
	@Override
	public Iterable<T> iterate();
	
	public T add(T task);
	
	@Override
	public T add(String description);



}
