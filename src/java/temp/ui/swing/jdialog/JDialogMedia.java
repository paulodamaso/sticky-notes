package temp.ui.swing.jdialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import temp.ui.PrintMedia;
import temp.ui.swing.jdialog.old.TextSticker;

public final class JDialogMedia  implements PrintMedia {
	
	private final JDialog dialog;
	private final TextSticker origin;
	
	public JDialogMedia(TextSticker sticker) {
		this.dialog = new JDialog();
		this.origin = sticker;
		this.dialog.getContentPane().add(sticker.textArea(), BorderLayout.CENTER);
		this.dialog.pack();

	}

	@Override
	public void print() {
		if (!this.dialog.isVisible())
			this.dialog.setVisible(true);
	}

}
