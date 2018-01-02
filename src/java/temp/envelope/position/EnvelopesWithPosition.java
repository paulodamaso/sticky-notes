package temp.envelope.position;

import java.util.Collection;

import temp.envelope.Envelopes;

public interface EnvelopesWithPosition extends Envelopes {
	
	public Collection<EnvelopeWithPosition> iterateInPosition();

}
