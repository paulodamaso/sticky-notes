package main.envelope.size.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;

public class DerbyEnvelopeWithSizeFactory implements EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> {

	private final String database;
	
	public DerbyEnvelopeWithSizeFactory(String database) {
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			System.out.println("Checking 'envelopewithsize' table in database...");
			boolean good = check();
			
			if (!good) {
				System.out.println("'envelopewithsize' table not found, creating...");
				init();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public EnvelopeWithSize create(EnvelopeWithSize envelope) {
		EnvelopeWithSize enveSize = new DerbyEnvelopeWithSize(envelope.origin(), this.database);
		return enveSize.size(envelope.size());
	}

	@Override
	public EnvelopesWithSize createEnvelopes(Envelopes envelopes) {
		EnvelopesWithSize envelopesWithSize = new DerbyEnvelopesWithSize(envelopes, database);
		return envelopesWithSize;
	}
	
	@Override
	public boolean check() {
	    boolean found = false;
		try {
		    ResultSet rs = connect().getMetaData().getTables(null, null, null, null);

		    while (rs.next())
		    {
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithsize")) {
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

	private final String create_query = "create TABLE envelopewithsize\r\n" + 
										"\r\n" + 
										"(    \r\n" + 
										"   id INT CONSTRAINT envelopewithsize_foreignkey\r\n" + 
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" + 
										"   width int, height int    \r\n" + 
										")";
	
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println("created 'envelopewithsize' table");
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
