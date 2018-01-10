package main.envelope.position.derby;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.position.EnvelopeWithPosition;
import main.envelope.position.EnvelopesWithPosition;

public class DerbyEnvelopeWithPositionFactory implements EnvelopeFactory<EnvelopeWithPosition, EnvelopesWithPosition> {

	private final String database;
	
	public DerbyEnvelopeWithPositionFactory(String database) {
		this.database = database;
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

}
