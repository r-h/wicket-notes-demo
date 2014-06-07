package io.github.r_h.wicket_notes_demo.data.note;

import io.github.r_h.wicket_notes_demo.data.dao.AbstractDaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.orm.jpa.JpaCallback;
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
		return getJpaTemplate().execute(new JpaCallback<List<Note>>() {
			public List<Note> doInJpa(EntityManager em)
					throws PersistenceException {
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Note> select = builder.createQuery(Note.class);
				Root<Note> note = select.from(Note.class);
				Path<Object> sort = note.get(sortProperty);
				Order order = null;
				if (sortAscending) {
					order = builder.asc(sort);
				} else {
					order = builder.desc(sort);
				}
				select.orderBy(order);

				TypedQuery<Note> query = em.createQuery(select);

				// TypedQuery<Note> query = em.createQuery(
				// "from Note order by " + sortProperty + " "
				// + (sortAscending ? "asc" : "desc"), Note.class);

				query.setFirstResult(offset);
				query.setMaxResults(count);
				return query.getResultList();
			}
		});
	}

}
