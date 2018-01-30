package ui.console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

import main.Application;
import main.Configuration;
import main.envelope.Envelope;
import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.SimpleEnvelope;
import main.envelope.SimpleEnvelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.color.derby.DerbyEnvelopeWithColorFactory;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.envelope.font.SimpleEnvelopeWithFont;
import main.envelope.font.derby.DerbyEnvelopeWithFontFactory;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;
import main.envelope.position.SimpleEnvelopeWithPosition;
import main.envelope.position.derby.DerbyEnvelopeWithPositionFactory;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;
import main.envelope.size.SimpleEnvelopeWithSize;
import main.envelope.size.derby.DerbyEnvelopeWithSizeFactory;
import main.i18n.Messages;
import main.note.Notes;
import ui.MediaFactory;
import ui.PrintMedia;

/**
 * <p> Command line interface for sticky-notes. 
 * 
 * @author paulodamaso
 *
 */
public final class CommandLineApplication implements Application {
	
	private final Notes notes;
	private final Configuration config;
	private final Envelopes envelopes;
	
	private final EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory = new DerbyEnvelopeWithColorFactory("resources/database/sticky-notes-db"); //$NON-NLS-1$
	private final EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory = new DerbyEnvelopeWithFontFactory("resources/database/sticky-notes-db"); //$NON-NLS-1$
	private final EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory = new DerbyEnvelopeWithPositionFactory("resources/database/sticky-notes-db"); //$NON-NLS-1$
	private final EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory = new DerbyEnvelopeWithSizeFactory("resources/database/sticky-notes-db"); //$NON-NLS-1$
	
	private final MediaFactory mediaFactory = new ConsoleMediaFactory();
	
//	private final MediaFactory mediaFactory = new ConsoleMediaFactory();

	public CommandLineApplication(Configuration config) {
		this.config = config;
		this.notes = this.config.notes();
		
    	this.envelopes = 
    			sizeFactory.createEnvelopes(
    					positionFactory.createEnvelopes(
    							fontFactory.createEnvelopes(
    									colorFactory.createEnvelopes(
    											new SimpleEnvelopes(this.notes)
    											)
    									)
    							)
    					);
	}

	@Override
	public Envelopes envelopes() {
		return envelopes;
	}

	@Override
	public Application start() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println(">"); //$NON-NLS-1$
		
		while (scan.hasNextLine()) {
			System.out.println(">"); //$NON-NLS-1$
			String line = scan.nextLine();
			
			if (line.startsWith("add ")) { //$NON-NLS-1$
				Collection<String> arguments =  new ArrayList<>();
			
				String[] split = line.split(" "); //$NON-NLS-1$
				boolean text = false;
				StringBuffer txtString = new StringBuffer();
				
				for (String str : split) {
					if (str.startsWith("\"")) {  //$NON-NLS-1$
						text = true;						
					}
					if (text) {
						txtString.append(str).append(" "); //$NON-NLS-1$
					} else {
						arguments.add(str);
					}
					
					if (str.endsWith("\"")) { //$NON-NLS-1$
						arguments.add(txtString.toString().replace("\"", "")); //$NON-NLS-1$ //$NON-NLS-2$
						text = false;
						txtString = new StringBuffer();		
					}
				}
				
				Envelope envelope = null;
				Iterator<String> it = arguments.iterator();
			
				while (it.hasNext()) {
					String str = it.next();
					if (str.equalsIgnoreCase("add")) { //$NON-NLS-1$
						String noteText = it.next();
//						System.out.println("Adding a note " + noteText);
						envelope = new SimpleEnvelope(notes.add(noteText));
					} else if (str.equalsIgnoreCase("color")) { //$NON-NLS-1$
						Color color = new Color (
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next())
								);
						envelope = colorFactory.create(new SimpleEnvelopeWithColor(envelope, color));
//						System.out.println("Decorating with color " + color);
					} else if (str.equalsIgnoreCase("font")) { //$NON-NLS-1$
						Font font = new Font(
								it.next(),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = fontFactory.create(new SimpleEnvelopeWithFont (envelope, font));

//						System.out.println("Decorating with font " + font);
					} else if (str.equalsIgnoreCase("position")) { //$NON-NLS-1$
						Point position = new Point(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = positionFactory.create(new SimpleEnvelopeWithPosition (envelope, position));
//						System.out.println("Decorating with position " + position);
					}  else if (str.equalsIgnoreCase("size")) { //$NON-NLS-1$
						Dimension size = new Dimension(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = sizeFactory.create(new SimpleEnvelopeWithSize (envelope, size));
						//System.out.println("Decorating with size " + size);
					}
					//i'm adding a new note. need to know which note to add and which decorations use
					//now I add all decorations
				}
			} else if (line.equalsIgnoreCase("print")) { //$NON-NLS-1$
				//print all envelopes
				for (Envelope enve : envelopes.iterate()) {
//					enve.printDecorations(enve);
					PrintMedia pm =	mediaFactory.create(enve);
					enve.print(pm);
				}

			} else if (line.equalsIgnoreCase("exit")) { //$NON-NLS-1$
				System.out.println(Messages.getString("CommandLineApplication.exit")); //$NON-NLS-1$
				scan.close();
				System.exit(0);
			} else if (line.equalsIgnoreCase("about")) {  //$NON-NLS-1$
				System.out.println(config.about());
			} else if (line.startsWith("locale")) {  //$NON-NLS-1$
				String[] split = line.split(" "); //$NON-NLS-1$
				this.config.locale(new Locale(split[1]));
			} else {
				System.out.println(Messages.getString("CommandLineApplication.unrecognizedCommand") + line); //$NON-NLS-1$
			}
				
		}
		return null;
	}
	
	@Override
	public EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory() {
		return colorFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory() {
		return fontFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory() {
		return positionFactory;
	}

	@Override
	public EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory() {
		return sizeFactory;
	}

	@Override
	public MediaFactory mediaFactory() {
		return mediaFactory;
	}

}
