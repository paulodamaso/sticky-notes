package main.envelope.font.derby;

import main.envelope.EnvelopeFactory;
import main.envelope.Envelopes;
import main.envelope.font.EnvelopeWithFont;
import main.envelope.font.EnvelopesWithFont;

public class DerbyEnvelopeWithFontFactory implements EnvelopeFactory<EnvelopeWithFont, EnvelopesWithFont> {
	
	private final String database;
	
	public DerbyEnvelopeWithFontFactory(String database) {
		this.database = database;
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

}
