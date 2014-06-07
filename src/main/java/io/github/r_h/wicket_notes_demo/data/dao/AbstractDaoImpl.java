package io.github.r_h.wicket_notes_demo.data.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * Implementation of the DAO.
 * 
 * @param <T>
 *            type of the object
 */
public abstract class AbstractDaoImpl<T extends Serializable> extends
		JpaDaoSupport implements Dao<T> {

	private final Class<T> type;

	public AbstractDaoImpl(Class<T> type) {
		this.type = type;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T load(Serializable id) {
		return getJpaTemplate().find(type, id);
	}

	@TransactionAttribute
	public T save(T object) {
		return getJpaTemplate().merge(object);
	}

	@TransactionAttribute
	public void delete(T object) {
		T merged = save(object);
		getJpaTemplate().remove(merged);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Set<T> findAll() {
		return getJpaTemplate().execute(new JpaCallback<Set<T>>() {
			public Set<T> doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<T> query = em.createQuery(
						"select x from " + type.getName() + " x", type);
				return new HashSet<T>(query.getResultList());
			}
		});
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public long countAll() {
		return getJpaTemplate().execute(new JpaCallback<Integer>() {
			public Integer doInJpa(EntityManager em)
					throws PersistenceException {
				TypedQuery<Long> query = em.createQuery("select count(x) from "
						+ type.getName() + " x", Long.class);
				return (query.getSingleResult()).intValue();
			}
		});
	}

}
