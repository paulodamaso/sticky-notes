package main.ui.swing;

import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import main.PrintableTask;

public interface StickerTask extends PrintableTask  {

	public abstract JTextArea txtDescription();
	
	public abstract JPopupMenu popUpMenu();
}
