package main.envelope;

public interface EnvelopeFactory<T extends Envelope, U extends Envelopes> {

	public T create (T envelope);
	public U createEnvelopes (Envelopes envelopes);
	
//	public EnvelopeWithFont create (Envelope envelope, Font font);
//	public EnvelopeWithPosition create (Envelope envelope, Point point);
//	public EnvelopeWithSize create (Envelope envelope, Dimension size);
	
}
