package io.github.r_h.wicket_notes_demo.pages.note;

import io.github.r_h.wicket_notes_demo.data.note.Note;
import io.github.r_h.wicket_notes_demo.data.note.NoteDao;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class SortableNotesDataProvider extends SortableDataProvider<Note> {

	private final NoteDao dao;

	public SortableNotesDataProvider(NoteDao dao) {
		this.dao = dao;
	}

	@Override
	public Iterator<Note> iterator(int first, int count) {
		SortParam sp = getSort();
		return dao.findAll(first, count, sp.getProperty(), sp.isAscending())
				.iterator();
	}

	@Override
	public int size() {
		return (int) dao.countAll();
	}

	@Override
	public IModel<Note> model(Note object) {
		return Model.of(object);
	}

}