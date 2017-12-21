package main.ui;

import main.Task;
import main.Tasks;

public interface PrintableTasks<T extends PrintableTask> extends Tasks<T>, Printable {

}
