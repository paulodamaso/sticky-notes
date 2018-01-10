package main.envelope.color.derby;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.color.EnvelopeWithColor;
import main.envelope.color.EnvelopesWithColor;

public final class DerbyEnvelopeWithColorFactory implements EnvelopeFactory<EnvelopeWithColor, EnvelopesWithColor> {
	
	private final String database;
	
	public DerbyEnvelopeWithColorFactory(String database) {
		this.database = database;
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
}
