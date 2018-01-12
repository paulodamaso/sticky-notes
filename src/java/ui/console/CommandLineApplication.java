package ui.console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import main.note.Notes;
import ui.MediaFactory;
import ui.PrintMedia;

/**
 * <p> Command line interface for sticky-notes. 
 * 
 * @author paulodamaso
 *
 */
public class CommandLineApplication implements Application {
	
	private final Notes notes;
	private final Configuration config;
	private final Envelopes envelopes;
	
	private final EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> colorFactory = new DerbyEnvelopeWithColorFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> fontFactory = new DerbyEnvelopeWithFontFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> positionFactory = new DerbyEnvelopeWithPositionFactory("resources/database/sticky-notes-db");
	private final EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> sizeFactory = new DerbyEnvelopeWithSizeFactory("resources/database/sticky-notes-db");
	
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
		System.out.println(">");
		
		while (scan.hasNextLine()) {
			System.out.println(">");
			String line = scan.nextLine();
			
			if (line.startsWith("add ")) {
				Collection<String> arguments =  new ArrayList<>();
			
				String[] split = line.split(" ");
				boolean text = false;
				StringBuffer txtString = new StringBuffer();
				
				for (String str : split) {
					if (str.startsWith("\"")) { 
						text = true;						
					}
					if (text) {
						txtString.append(str).append(" ");
					} else {
						arguments.add(str);
					}
					
					if (str.endsWith("\"")) {
						arguments.add(txtString.toString().replace("\"", ""));
						text = false;
						txtString = new StringBuffer();		
					}
				}
				
				Envelope envelope = null;
				Iterator<String> it = arguments.iterator();
			
				while (it.hasNext()) {
					String str = it.next();
					if (str.equalsIgnoreCase("add")) {
						String noteText = it.next();
//						System.out.println("Adding a note " + noteText);
						envelope = new SimpleEnvelope(notes.add(noteText));
					} else if (str.equalsIgnoreCase("color")) {
						Color color = new Color (
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next())
								);
						envelope = colorFactory.create(new SimpleEnvelopeWithColor(envelope, color));
//						System.out.println("Decorating with color " + color);
					} else if (str.equalsIgnoreCase("font")) {
						Font font = new Font(
								it.next(),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = fontFactory.create(new SimpleEnvelopeWithFont (envelope, font));

//						System.out.println("Decorating with font " + font);
					} else if (str.equalsIgnoreCase("position")) {
						Point position = new Point(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = positionFactory.create(new SimpleEnvelopeWithPosition (envelope, position));
//						System.out.println("Decorating with position " + position);
					}  else if (str.equalsIgnoreCase("size")) {
						Dimension size = new Dimension(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = sizeFactory.create(new SimpleEnvelopeWithSize (envelope, size));
						//System.out.println("Decorating with size " + size);
					}
					//i'm adding a new note. need to know which note to add and which decorations use
					//now I add all decorations
				}
			} else if (line.equalsIgnoreCase("print")) {
				//print all envelopes
				for (Envelope enve : envelopes.iterate()) {
//					enve.printDecorations(enve);
					PrintMedia pm =	mediaFactory.create(enve);
					enve.print(pm);
				}

			} else if (line.equalsIgnoreCase("exit")) {
				System.out.println("Exiting....");
				scan.close();
				System.exit(0);
			} else if (line.equalsIgnoreCase("about")) {
				System.out.println(config.about());
				scan.close();
				System.exit(0);
			}else {
				System.out.println("Unrecognized command " + line);
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
