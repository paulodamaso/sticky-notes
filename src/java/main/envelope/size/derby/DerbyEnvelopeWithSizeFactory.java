package main.envelope.size.derby;

import main.envelope.EnvelopeFactory;
import main.envelope.size.EnvelopeWithSize;
import main.envelope.size.EnvelopesWithSize;

public class DerbyEnvelopeWithSizeFactory implements EnvelopeFactory<EnvelopeWithSize, EnvelopesWithSize> {

	private final String database;
	
	public DerbyEnvelopeWithSizeFactory(String database) {
		this.database = database;
	}

	@Override
	public EnvelopeWithSize create(EnvelopeWithSize envelope) {
		EnvelopeWithSize enveSize = new DerbyEnvelopeWithSize(envelope.origin(), this.database);
		return enveSize.size(envelope.size());
	}

	@Override
	public EnvelopesWithSize createEnvelopes(EnvelopesWithSize envelopes) {
		EnvelopesWithSize envelopesWithSize = new DerbyEnvelopesWithSize(envelopes.origin(), database);
		return envelopesWithSize;
	}

}
