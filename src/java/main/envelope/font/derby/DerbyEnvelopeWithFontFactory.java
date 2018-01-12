package main.envelope.font.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;

/**
 * <p> Implementation of {@link EnvelopeFactory} for creation of {@link EnvelopeWithFont} persisted in a derby database.
 * 
 * @author paulodamaso
 *
 */
public class DerbyEnvelopeWithFontFactory implements EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> {
	
	private final String database;
	
	public DerbyEnvelopeWithFontFactory(String database) {
		this.database = database;
	
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			System.out.println("Checking 'envelopewithcolor' table in database...");
			boolean good = check();
			
			if (!good) {
				System.out.println("'envelopewithcolor' table not found, creating...");
				init();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public EnvelopeWithFont create(EnvelopeWithFont envelope) {
		EnvelopeWithFont enveFont = new DerbyEnvelopeWithFont(envelope.origin(), this.database);
		return enveFont.font(envelope.font());
	}

	@Override
	public EnvelopesWithFont createEnvelopes(Envelopes envelopes) {
		EnvelopesWithFont envelopesWithFont = new DerbyEnvelopesWithFont(envelopes, database);
		return envelopesWithFont;
	}
	
	@Override
	public boolean check() {
	    boolean found = false;
		try {
		    ResultSet rs = connect().getMetaData().getTables(null, null, null, null);

		    while (rs.next())
		    {
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithfont")) {
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

	private final String create_query = "create TABLE envelopewithfont\r\n" + 
										"\r\n" + 
										"(    \r\n" + 
										"   id INT CONSTRAINT envelopewithfont_foreignkey\r\n" + 
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" + 
										"   name varchar(256), style int, size int    \r\n" + 
										")";
	
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println("created 'envelopewithfont' table");
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

}
