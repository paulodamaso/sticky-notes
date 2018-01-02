package temp.ui.jdialog;

import java.util.ArrayList;
import java.util.Collection;

import main.note.Note;
import main.note.Stickers;
import temp.ui.PrintedSticker;

/**
 * <p> Simple implementation of a {@link JDialogStickers}.
 * 
 * @author paulodamaso
 *
 */
public final class SimpleJDialogStickers implements JDialogStickers {
	
	private final Stickers origin;

	public SimpleJDialogStickers(Stickers origin) {
		this.origin = origin;
	}

	@Override
	public void print() {
//		//System.out.println("Printing SimpleJDialogStickers");
		for (PrintedSticker stk : notes()) {
			stk.print();
		}
//		//System.out.println("Ended Printing SimpleJDialogStickers");
	}

	@Override
	public Collection<JDialogSticker> iterate() {
//		//System.out.println("Iterating in SimplejDialogStickers");
		ArrayList<JDialogSticker> stickers = new ArrayList<JDialogSticker>();
		for(Note stk : origin.notes()) {
			stickers.add(new SimpleSticker(stk));
		}
//		//System.out.println("Iterated in SimplejDialogStickers");
		return stickers;
	}

	@Override
	public JDialogSticker add(String text) {
		return new SimpleSticker(origin.add(text));
	}

	@Override
	public Stickers stickers() {
		return origin;
	}
}
