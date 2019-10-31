package io.github.r_h.wicket_notes_demo.data.note;

import io.github.r_h.wicket_notes_demo.data.dao.AbstractDaoImpl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

/**
 * Implementation of the Dao for a Note.
 */
@Component
public class NoteDaoImpl extends AbstractDaoImpl<Note> implements NoteDao {

	public NoteDaoImpl() {
		super(Note.class);
	}

	public List<Note> findAll(final int offset, final int count,
			final String sortProperty, final boolean sortAscending) {
				
				/* breaks with user.name sorting.....*/
//				CriteriaBuilder builder = em.getCriteriaBuilder();
//				CriteriaQuery<Note> select = builder.createQuery(Note.class);
//				Root<Note> note = select.from(Note.class);				
//				Path<Object> sort = note.get(sortProperty);
//				Order order = null;
//				if (sortAscending) {
//					order = builder.asc(sort);
//				} else {
//					order = builder.desc(sort);
//				}
//				select.orderBy(order);
//
//				TypedQuery<Note> query = em.createQuery(select);
				
				/* workaround for user.name sorting... */

				 TypedQuery<Note> query = getEntityManager().createQuery(
				 "from Note order by " + sortProperty + " "
				 + (sortAscending ? "asc" : "desc"), Note.class);

				query.setFirstResult(offset);
				query.setMaxResults(count);
				return query.getResultList();
	}

}
