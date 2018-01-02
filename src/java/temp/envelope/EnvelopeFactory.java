package temp.envelope;

import main.envelope.color.EnvelopesWithColor;
import main.envelope.color.derby.DerbyEnvelopesWithColor;
import main.envelope.font.EnvelopesWithFont;
import main.envelope.font.derby.DerbyEnvelopesWithFont;
import temp.envelope.position.EnvelopesWithPosition;
import temp.envelope.position.derby.DerbyEnvelopesWithPosition;

public class EnvelopeFactory {
	
	public EnvelopesWithColor envelopesWithColor(Envelopes envelopes) {
		return new DerbyEnvelopesWithColor(envelopes, "resources/database/sticky-notes-db");
	}
	
	public EnvelopesWithFont envelopesWithFont (Envelopes envelopes) {
		return new DerbyEnvelopesWithFont (envelopes, "resources/database/sticky-notes-db"); 
	}
	public EnvelopesWithPosition envelopesWithPosition (Envelopes envelopes) {
		return new DerbyEnvelopesWithPosition (envelopes, "resources/database/sticky-notes-db");
	}
	//public abstract EnvelopesWithSize envelopesWithSize(Envelopes envelopes);

}
