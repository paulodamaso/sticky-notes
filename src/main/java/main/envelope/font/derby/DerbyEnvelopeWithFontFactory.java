package main.envelope.font.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;
import main.i18n.Messages;

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
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			
			System.out.println(Messages.getString("DerbyEnvelopeWithFontFactory.checkingTable")); //$NON-NLS-1$
			boolean good = check();
			
			if (!good) {
				System.out.println(Messages.getString("DerbyEnvelopeWithFontFactory.tableNotFound")); //$NON-NLS-1$
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
		      if(rs.getString("TABLE_NAME").toLowerCase().equalsIgnoreCase("envelopewithfont")) { //$NON-NLS-1$ //$NON-NLS-2$
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

	private final String create_query = "create TABLE envelopewithfont\r\n" +  //$NON-NLS-1$
										"\r\n" +  //$NON-NLS-1$
										"(    \r\n" +  //$NON-NLS-1$
										"   id INT CONSTRAINT envelopewithfont_foreignkey\r\n" +  //$NON-NLS-1$
										"	REFERENCES note ON DELETE CASCADE ON UPDATE RESTRICT,   \r\n" +  //$NON-NLS-1$
										"   name varchar(256), style int, size int    \r\n" +  //$NON-NLS-1$
										")"; //$NON-NLS-1$
	
	@Override
	public void init() {
		Connection conn = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(create_query);
			ps.executeUpdate();
			System.out.println(Messages.getString("DerbyEnvelopeWithFontFactory.tableCreated")); //$NON-NLS-1$
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
