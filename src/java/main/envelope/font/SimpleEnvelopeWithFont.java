package main.envelope.font;

import java.awt.Font;

import main.envelope.Envelope;

public final class SimpleEnvelopeWithFont implements EnvelopeWithFont {
	
	private final Envelope origin;
	private final Font font;
			

	public SimpleEnvelopeWithFont(Envelope origin, Font font) {
		this.origin = origin;
		this.font = font;
	}

	@Override
	public int id() {
		return origin.id();
	}

	@Override		
	public String text() {
		return origin.text();
	}

	@Override
	public void text(String text) {
		origin.text(text);
	}

	@Override
	public Envelope origin() {
		return origin;
	}

	@Override
	public Font font() {
		return font;
	}

	@Override
	public EnvelopeWithFont font(Font font) {
		return new SimpleEnvelopeWithFont(origin, font);
	}

}
