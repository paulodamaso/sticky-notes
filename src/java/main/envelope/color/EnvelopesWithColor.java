package main.envelope.color;

import java.util.Collection;

import main.envelope.Envelopes;

public interface EnvelopesWithColor extends Envelopes {

	public Collection<EnvelopeWithColor> iterateInColor();
}
