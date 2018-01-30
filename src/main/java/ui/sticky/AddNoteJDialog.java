package ui.sticky;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import main.Application;
import main.envelope.Envelope;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.font.SimpleEnvelopeWithFont;
import main.envelope.position.SimpleEnvelopeWithPosition;
import main.envelope.size.SimpleEnvelopeWithSize;
import main.i18n.Messages;

class NoTabTextArea extends JTextArea {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoTabTextArea() {}
  
    protected void processComponentKeyEvent( KeyEvent e ) {
        if ( e.getID() == KeyEvent.KEY_PRESSED && 
                 e.getKeyCode() == KeyEvent.VK_TAB ) {
            e.consume();
            if (e.isShiftDown()) {
                transferFocusBackward();
            } else {
                transferFocus();
            }  
        } else {
            super.processComponentKeyEvent( e );
        }
    }  
}

public final class AddNoteJDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Application app;
	private Envelope enve;

	public AddNoteJDialog(Application app) {
		this.app = app;
		this.setTitle(Messages.getString("addNoteJdialog.font")); //$NON-NLS-1$
		this.getContentPane().setLayout(new GridBagLayout());

		//text value
		Label label = new Label(Messages.getString("addNoteJdialogText.label")); //$NON-NLS-1$
		this.getContentPane().add(label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		JTextArea text = new NoTabTextArea();
		text.setLineWrap(true);
		this.getContentPane().add(text, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 20));
		
		//Color value
		Label colorItem = new Label(Messages.getString("addNoteJdialogColor.label")); //$NON-NLS-1$
		this.getContentPane().add(colorItem, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		JTextArea colorValue = new NoTabTextArea();
		this.getContentPane().add(colorValue, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));

		//Font value
		Label fontItem = new Label(Messages.getString("addNoteJdialogFont.label")); //$NON-NLS-1$
		this.getContentPane().add(fontItem, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		JTextArea fontValue = new NoTabTextArea();
		this.getContentPane().add(fontValue, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		
		//Size value
		Label sizeItem = new Label(Messages.getString("addNoteJdialogSize.label")); //$NON-NLS-1$
		this.getContentPane().add(sizeItem, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		JTextArea sizeValue = new NoTabTextArea();
		this.getContentPane().add(sizeValue, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));		
		
		
		Label positionItem = new Label(Messages.getString("addNoteJdialogPosition.label")); //$NON-NLS-1$
		this.getContentPane().add(positionItem, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));
		JTextArea positionValue = new NoTabTextArea();
		this.getContentPane().add(positionValue, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(5,5,5,5), 0, 0));

		JButton buttonAdd = new JButton(Messages.getString("addNoteJdialogAddButon.label")); //$NON-NLS-1$
		buttonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Envelope envelope = AddNoteJDialog.this.app.envelopes().add( text.getText());
				
				if (colorValue.getText() != null && !colorValue.getText().contentEquals(new StringBuffer())) {
					String[] rgb = colorValue.getText().split(","); //$NON-NLS-1$
					envelope = AddNoteJDialog.this.app.colorFactory().create(new SimpleEnvelopeWithColor(envelope, new Color(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()))));
				}
				if (fontValue.getText() != null && !fontValue.getText().contentEquals(new StringBuffer())) {
					String[] font = colorValue.getText().split(","); //$NON-NLS-1$
					envelope = AddNoteJDialog.this.app.fontFactory().create(new SimpleEnvelopeWithFont(envelope, new Font(font[0], Integer.parseInt(font[1].trim()), Integer.parseInt(font[2].trim()))));
				}
				if (positionValue.getText() != null && !positionValue.getText().contentEquals(new StringBuffer())) {
					String[] position = positionValue.getText().split(","); //$NON-NLS-1$
					envelope = AddNoteJDialog.this.app.positionFactory().create(new SimpleEnvelopeWithPosition(envelope, new Point(Integer.parseInt(position[0].trim()), Integer.parseInt(position[1].trim()))));
				}
				if (sizeValue.getText() != null && !sizeValue.getText().contentEquals(new StringBuffer())) {
					String[] size = sizeValue.getText().split(","); //$NON-NLS-1$
					envelope = AddNoteJDialog.this.app.sizeFactory().create(new SimpleEnvelopeWithSize(envelope, new Dimension(Integer.parseInt(size[0].trim()), Integer.parseInt(size[1].trim()))));
				}
				
				AddNoteJDialog.this.enve = envelope;
				
				AddNoteJDialog.this.dispose();
			}
		});
		this.getContentPane().add(buttonAdd, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10,10,10,10), 0, 0));
		JButton buttonCancel = new JButton(Messages.getString("addNoteJdialogCancelButon.label")); //$NON-NLS-1$
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNoteJDialog.this.dispose();
			}
		});
		this.getContentPane().add(buttonCancel, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(10,10,10,10), 0, 0));
		
		this.pack();
		this.setVisible(true);
	}
	
	public Envelope envelope() {
		return enve;
	}


}
