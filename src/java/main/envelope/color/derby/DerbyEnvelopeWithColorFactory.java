package main.envelope.color.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;

/**
 * <p> Implementation of {@link EnvelopeFactory} for creation of {@link EnvelopeWithColor} persisted in a derby database.
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopeWithColorFactory implements EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> {
	
	private final String database;
	
	
	public DerbyEnvelopeWithColorFactory(String database) {
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
	public EnvelopeWithColor create(EnvelopeWithColor envColor) {
		EnvelopeWithColor enveC = new DerbyEnvelopeWithColor(envColor.origin(), this.database);
		return enveC.color(envColor.color());
	}

	@Override
	public EnvelopesWithColor createEnvelopes(Envelopes envelopes) {
		EnvelopesWithColor envelopesWithColor = new DerbyEnvelopesWithColor(envelopes, database);
		return envelopesWithColor;
	}

	
	@Override
	public boolean check() {
	    boolean found = false;
		try {
		    ResultSet rs = connect().getMetaData().getTables(null, null, null, null);

		    while (rs.next())
		    {
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithcolor")) {
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

	private final String create_query = "create TABLE envelopewithcolor\r\n" + 
										"\r\n" + 
										"(    \r\n" + 
										"   id INT CONSTRAINT envelopewithcolor_foreignkey\r\n" + 
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" + 
										"   red int, green int, blue int    \r\n" + 
										")";
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println("created 'envelopewithcolor' table");
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
