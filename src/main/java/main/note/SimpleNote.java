package main.note;

/**
 * <p> Basic {@link Note} implementation without persisted data.
 * 
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
	public int id() {
		return this.id;
	}

	@Override
	public String text() {
		return this.text;
	}

	@Override
	public void text(String text) {
		//what to do here?
	}

	@Override
	public void delete() {
		//and here?
	}

}
