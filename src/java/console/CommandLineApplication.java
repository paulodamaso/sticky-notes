package console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import console.media.ConsoleMediaFactoryImpl;
import main.Application;
import main.envelope.Envelope;
import main.envelope.Envelopes;
import main.envelope.SimpleEnvelope;
import main.envelope.SimpleEnvelopes;
import main.envelope.color.SimpleEnvelopeWithColor;
import main.envelope.color.derby.DerbyEnvelopesWithColor;
import main.envelope.font.SimpleEnvelopeWithFont;
import main.envelope.font.derby.DerbyEnvelopesWithFont;
import main.envelope.media.PrintMedia;
import main.envelope.position.SimpleEnvelopeWithPosition;
import main.envelope.position.derby.DerbyEnvelopesWithPosition;
import main.envelope.size.SimpleEnvelopeWithSize;
import main.envelope.size.derby.DerbyEnvelopesWithSize;
import main.note.Notes;
import main.note.SimpleNote;

/**
 * <p> Command line interface for sticky-notes. 
 * 
 * @author paulodamaso
 *
 */
public class CommandLineApplication implements Application {
	
	private final Notes notes;
	private final Envelopes envelopes;

	public CommandLineApplication(Notes notes) {
		this.notes = notes;
		
    	this.envelopes = 
    			new DerbyEnvelopesWithSize(
    				new DerbyEnvelopesWithPosition(
    					new DerbyEnvelopesWithFont(
    						new DerbyEnvelopesWithColor( 
    							new SimpleEnvelopes(this.notes), 
    						"resources/database/sticky-notes-db"), 
	    				"resources/database/sticky-notes-db"),
    				"resources/database/sticky-notes-db"),
    			"resources/database/sticky-notes-db");
	}

	@Override
	public Envelope add(Envelope envelope) {
		return envelopes.add(envelope);
	}

	@Override
	public Envelopes envelopes() {
		return envelopes;
	}

	@Override
	public Application init() throws Exception {
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
						envelope = new SimpleEnvelope(new SimpleNote(0, noteText));
					} else if (str.equalsIgnoreCase("color")) {
						Color color = new Color (
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next())
								);
						envelope = new SimpleEnvelopeWithColor(envelope, color);
//						System.out.println("Decorating with color " + color);
					} else if (str.equalsIgnoreCase("font")) {
						Font font = new Font(
								it.next(),
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = new SimpleEnvelopeWithFont (envelope, font);
//						System.out.println("Decorating with font " + font);
					} else if (str.equalsIgnoreCase("position")) {
						Point position = new Point(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = new SimpleEnvelopeWithPosition (envelope, position);
//						System.out.println("Decorating with position " + position);
					}  else if (str.equalsIgnoreCase("size")) {
						Dimension size = new Dimension(
								Integer.parseInt(it.next()),
								Integer.parseInt(it.next()));
						envelope = new SimpleEnvelopeWithSize (envelope, size);
						//System.out.println("Decorating with size " + size);
					}
					//i'm adding a new note. need to know which note to add and which decorations use
					//now I add all decorations
				}
				add(envelope);
			} else if (line.equalsIgnoreCase("print")) {
				//print all envelopes
				for (Envelope enve : envelopes.iterate()) {
					PrintMedia pm = new ConsoleMediaFactoryImpl().create(enve);
					enve.print(pm);
				}

			} else if (line.equalsIgnoreCase("exit")) {
				System.out.println("Exiting....");
				scan.close();
				System.exit(0);
			} else {
				System.out.println("Unrecognized command " + line);
			}
				
		}
		return null;
	}

}
