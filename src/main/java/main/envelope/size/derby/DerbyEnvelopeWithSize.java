package main.envelope.size.derby;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.envelope.Envelope;
import main.envelope.size.EnvelopeWithSize;
import main.i18n.Messages;

/**
 * <p> {@link EnvelopeWithSize} implementation with size data in derby database, in table 'envelopewithsize'.
 * 
 * @author paulodamaso
 *
 */
public final class DerbyEnvelopeWithSize implements EnvelopeWithSize{
	
	/*
	 * @todo #129 internationalize DerbyEnvelopeWithSize log messages
	 */
	private static final Logger logger = Logger.getLogger( DerbyEnvelopeWithSize.class.getName() );
	
	private final Envelope origin;
	private final String database;

	public DerbyEnvelopeWithSize(Envelope origin, String database) {
		this.origin = origin;
		this.database = database;
		
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); //$NON-NLS-1$
			 
		}catch (Exception e){
			/* @todo #12 implement better exception handling in choosing database driver for DerbyEnvelopeWithSize
			 * 
			 */
			e.printStackTrace();
		}
	}
	
	private Connection connect() throws Exception {
		return DriverManager.getConnection("jdbc:derby:"+ database +";"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int id() {
		return origin.id();
	}

	private final String insert_size_query = "insert into envelopewithsize (id, width, height) values ( ?, ?, ?)"; //$NON-NLS-1$
	private final String update_size_query = "update envelopewithsize set width = ?, height = ? where id = ?"; //$NON-NLS-1$
	public DerbyEnvelopeWithSize size(Dimension size) {
		Connection conn = null;
		try {

			PreparedStatement ps = null;
			
			if (size() != null) {
				conn = connect();
				ps = conn.prepareStatement(update_size_query);
				ps.setInt(1, size.width);
				ps.setInt(2, size.height);
				ps.setInt(3, id());
			} else {
				conn = connect();
				ps = conn.prepareStatement(insert_size_query);
				ps.setInt(1, id());
				ps.setInt(2, size.width);
				ps.setInt(3, size.height);
			}

			ps.executeUpdate();
			
			return new DerbyEnvelopeWithSize(origin, database);

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when saving DerbyEnvelopeWithSize
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after saving DerbyEnvelopeWithSize.
				 * 
				 */
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	private final String size_query = "select width, height from envelopewithsize where id = ?"; //$NON-NLS-1$
	@Override
	public Dimension size() {
		Connection conn = null;
		Dimension size = null;
		try {
			conn = connect();
			PreparedStatement ps = conn.prepareStatement(size_query);
			ps.setInt(1, id());

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				size =  new Dimension(rs.getInt(1), rs.getInt(2));
			}

		}catch(Exception e) {
			/* @todo #12 implement better exception handling when retrieving size from DerbyEnvelopeWithSize
			 * 
			 */
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				/* @todo #12 implement better exception handling closing connection after retrieving size from DerbyEnvelopeWithSize
				 * 
				 */
				e.printStackTrace();
			}
		}
		return size;
	}
	
	@Override
	public String text() {
		return origin.text();
	}

	@Override
	public void text(String text) {
		origin.text(text);
	}

	@Override
	public Envelope origin() {
		return this.origin;
	}
	
	private final String delete_size_query = "update envelopewithsize set width = ?, height = ? where id = ?"; //$NON-NLS-1$
	public void delete() {
		Connection conn = null;
		try {

			PreparedStatement ps = null;

			conn = connect();
			ps = conn.prepareStatement(delete_size_query);

			ps.setInt(3, id());
			ps.executeUpdate();

		}catch(Exception e) {
			logger.log(Level.SEVERE, Messages.getString("DerbyEnvelopeWithSize.errorDeleting"), e); //$NON-NLS-1$
		}finally {
			try {
				conn.close();
			}catch(Exception e) {
				logger.log(Level.SEVERE, Messages.getString("DerbyEnvelopeWithSize.errorClosingConnection"), e); //$NON-NLS-1$
			}
		}
		origin.delete();
	}

}
