package main.note;

/**
 * <p> Basic {@link Note} implementation without persisted data.
 * @author paulodamaso
 *
 */
public final class SimpleNote implements Note {

	private final int id;
	private final String text;
	
	public SimpleNote(int id, String text) {
		this.id = id;
		this.text = text;
}

	@Override
	public Note persist(Note persistent) {
		System.out.println("I can't persist!");
		return this;
	}

	@Override
	public int id() {
		return this.id;
	}

	@Override
	public String text() {
		return this.text;
	}

}
