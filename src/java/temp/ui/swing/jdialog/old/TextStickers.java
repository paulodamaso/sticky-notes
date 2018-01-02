package temp.ui.swing.jdialog.old;

import java.util.ArrayList;
import java.util.Collection;

import main.note.Note;
import main.note.Stickers;

public class TextStickers implements Stickers {
	
	private final Stickers origin;

	public TextStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public Collection<Note> iterate() {
		Collection<Note> ret = new ArrayList<Note>();
		for(Note stk : origin.notes()) {
			ret.add(new SimpleTextSticker(stk));
		}
		return ret;
	}

	@Override
	public Note add(String text) {
		return origin.add(text);
	}

}
