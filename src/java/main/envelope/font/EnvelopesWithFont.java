package main.envelope.font;

import java.util.Collection;

import main.envelope.Envelopes;


public interface EnvelopesWithFont extends Envelopes {

	public Collection<EnvelopeWithFont> iterateInFont();
}
