package main;

import java.util.Locale;

import main.note.NoteFactory;
import main.note.Notes;
import main.note.persistence.derby.DerbyNoteFactory;

public final class Configuration {
	
	private final NoteFactory noteFactory;
	private final Locale[] locales = { new Locale("en_US"),  new Locale("pt_BR")}; //$NON-NLS-1$ //$NON-NLS-2$

	public Configuration() {
		this(Locale.getDefault());
	}
	
	public Configuration(Locale locale) {
		noteFactory = new DerbyNoteFactory("resources/database/sticky-notes-db");  //$NON-NLS-1$
		Messages.setLocale(Locale.getDefault());
	}
	
	public Notes notes () {
		return noteFactory.create();
	}
	
	public Application application() {
		//return new CommandLineApplication(this);
		return new SystemTrayApplication(this);
	} 
	
	public String about() {
		return Messages.getString("about.text"); //$NON-NLS-1$
	}
	
	public void locale(Locale locale) {
		Messages.setLocale(locale);
	}
	
	public Locale locale() {
		return Messages.locale();
	}
	
	public Locale[] locales() {
		return locales;
	}
}
