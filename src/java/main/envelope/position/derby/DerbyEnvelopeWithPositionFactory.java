package main.envelope.position.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;

public class DerbyEnvelopeWithPositionFactory implements EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> {

	private final String database;
	
	public DerbyEnvelopeWithPositionFactory(String database) {
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			System.out.println("Checking 'envelopewithposition' table in database...");
			boolean good = check();
			
			if (!good) {
				System.out.println("'envelopewithposition' table not found, creating...");
				init();
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}		
	}

	@Override
	public EnvelopeWithPosition create(EnvelopeWithPosition envelope) {
		EnvelopeWithPosition envePosition = new DerbyEnvelopeWithPosition(envelope.origin(), this.database);
		return envePosition.position(envelope.position());
	}

	@Override
	public EnvelopesWithPosition createEnvelopes(Envelopes envelopes) {
		EnvelopesWithPosition envelopesWithPosition = new DerbyEnvelopesWithPosition(envelopes, database);
		return envelopesWithPosition;
	}
	
	@Override
	public boolean check() {
	    boolean found = false;
		try {
		    ResultSet rs = connect().getMetaData().getTables(null, null, null, null);

		    while (rs.next())
		    {
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithposition")) {
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

	private final String create_query = "create TABLE envelopewithposition\r\n" + 
										"\r\n" + 
										"(    \r\n" + 
										"   id INT CONSTRAINT envelopewithposition_foreignkey\r\n" + 
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" + 
										"   x int, y int    \r\n" + 
										")";
	
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println("created 'envelopewithposition' table");
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
