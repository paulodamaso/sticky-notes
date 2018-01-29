package main.envelope.size.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Messages;
import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;

public class DerbyEnvelopeWithSizeFactory implements EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> {

	private final String database;
	
	public DerbyEnvelopeWithSizeFactory(String database) {
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			
			System.out.println(Messages.getString("DerbyEnvelopeWithSizeFactory.checkingTable")); //$NON-NLS-1$
			boolean good = check();
			
			if (!good) {
				System.out.println(Messages.getString("DerbyEnvelopeWithSizeFactory.tableNotFound")); //$NON-NLS-1$
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
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithsize")) { //$NON-NLS-1$ //$NON-NLS-2$
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

	private final String create_query = "create TABLE envelopewithsize\r\n" +  //$NON-NLS-1$
										"\r\n" +  //$NON-NLS-1$
										"(    \r\n" +  //$NON-NLS-1$
										"   id INT CONSTRAINT envelopewithsize_foreignkey\r\n" +  //$NON-NLS-1$
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" +  //$NON-NLS-1$
										"   width int, height int    \r\n" +  //$NON-NLS-1$
										")"; //$NON-NLS-1$
	
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println(Messages.getString("DerbyEnvelopeWithSizeFactory.tableCreated")); //$NON-NLS-1$
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
