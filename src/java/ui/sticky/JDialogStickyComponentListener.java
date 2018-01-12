package ui.sticky;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class JDialogStickyComponentListener implements ComponentListener {

	@Override
	public void componentResized(ComponentEvent e) {
		System.out.println("Resized " + e.getComponent().getSize());

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		System.out.println("Moved " + e.getComponent().getLocation());

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
