package io.github.r_h.wicket_notes_demo.data.note;

import io.github.r_h.wicket_notes_demo.data.dao.Dao;

import java.util.List;

/**
 * DAO for {@link Note}.
 */
public interface NoteDao extends Dao<Note> {

	public List<Note> findAll(int offset, int count, String sortProperty,
			boolean sortAscending);

}
