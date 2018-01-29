package main.envelope.color.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Messages;
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
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			
			System.out.println(Messages.getString("DerbyEnvelopeWithColorFactory.checkingTable")); //$NON-NLS-1$
			boolean good = check();
			
			if (!good) {
				System.out.println(Messages.getString("DerbyEnvelopeWithColorFactory.tableNotFound")); //$NON-NLS-1$
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
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithcolor")) { //$NON-NLS-1$ //$NON-NLS-2$
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

	private final String create_query = "create TABLE envelopewithcolor\r\n" +  //$NON-NLS-1$
										"\r\n" +  //$NON-NLS-1$
										"(    \r\n" +  //$NON-NLS-1$
										"   id INT CONSTRAINT envelopewithcolor_foreignkey\r\n" +  //$NON-NLS-1$
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" +  //$NON-NLS-1$
										"   red int, green int, blue int    \r\n" +  //$NON-NLS-1$
										")"; //$NON-NLS-1$
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println(Messages.getString("DerbyEnvelopeWithColorFactory.tableCreated")); //$NON-NLS-1$
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
