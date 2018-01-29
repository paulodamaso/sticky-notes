package ui.console;

import java.text.MessageFormat;

import main.Messages;
import main.envelope.Envelope;
import ui.PrintMedia;

/**
 * <p> Prints {@link Envelope} to console.
 * 
 * @author paulodamaso
 *
 */
public final class ConsoleSimpleMedia implements PrintMedia {
	
	private final Envelope envelope;
	
	public ConsoleSimpleMedia(Envelope envelope) {
		this.envelope = envelope;
	}
	
	@Override
	public void print() {
		Object[] messageArguments = {
				envelope.id(),
				envelope.text()
			};
		System.out.println(new MessageFormat(Messages.getString("ConsoleSimpleMedia.printText")).format(messageArguments));		 //$NON-NLS-1$
	}
}
