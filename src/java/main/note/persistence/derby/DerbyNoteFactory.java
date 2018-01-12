package main.note.persistence.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			System.out.println("Checking 'note' table in database...");
			boolean good = check();
			
			if (!good) {
				System.out.println("'note' table not found, creating...");
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
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("note")) {
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
		
		return DriverManager.getConnection("jdbc:derby:"+ database +";create=true");
	}

	private final String create_query = "create TABLE note\r\n" + 
										"\r\n" + 
										"(    \r\n" + 
										"   id INT not null primary key\r\n" + 
										"        GENERATED ALWAYS AS IDENTITY\r\n" + 
										"        (START WITH 1, INCREMENT BY 1),   \r\n" + 
										"   text VARCHAR(4096)    \r\n" + 
										")";
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println("created 'note' table");
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
