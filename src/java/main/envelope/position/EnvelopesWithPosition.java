package main.envelope.position;

import java.util.Collection;

import main.envelope.Envelopes;

public interface EnvelopesWithPosition extends Envelopes {
	
	public Collection<EnvelopeWithPosition> iterateInPosition();

}
