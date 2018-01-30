package main.note.persistence.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.i18n.Messages;
import main.note.Note;
import main.note.NoteFactory;
import main.note.Notes;

/**
 * <p> {@link Note} factory from crating {@link Note} and {@link Notes} objects from a derby database.
 *  
 * @author paulodamaso
 *
 */
public class DerbyNoteFactory implements NoteFactory {
	
	private String database;

	public DerbyNoteFactory(String database) {
		this.database = database;
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			
			System.out.println(Messages.getString("DerbyNoteFactory.checkingTable")); //$NON-NLS-1$
			boolean good = check();
			
			if (!good) {
				System.out.println(Messages.getString("DerbyNoteFactory.tableNotFound")); //$NON-NLS-1$
				init();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}		
	}

	@Override
	public boolean check() {
	    boolean found = false;
		try {
		    ResultSet rs = connect().getMetaData().getTables(null, null, null, null);

		    while (rs.next())
		    {
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("note")) { //$NON-NLS-1$ //$NON-NLS-2$
		    	  found = true;
		    	  break;
		      }
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return found;
	}
	
	private Connection connect() throws Exception {
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";create=true"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private final String create_query = "create TABLE note\r\n" +  //$NON-NLS-1$
										"\r\n" +  //$NON-NLS-1$
										"(    \r\n" +  //$NON-NLS-1$
										"   id INT not null primary key\r\n" +  //$NON-NLS-1$
										"        GENERATED ALWAYS AS IDENTITY\r\n" +  //$NON-NLS-1$
										"        (START WITH 1, INCREMENT BY 1),   \r\n" +  //$NON-NLS-1$
										"   text VARCHAR(4096)    \r\n" +  //$NON-NLS-1$
										")"; //$NON-NLS-1$
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println(Messages.getString("DerbyNoteFactory.tableCreated")); //$NON-NLS-1$
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Notes create() {
		return new DerbyNotes(database);
	}

}
