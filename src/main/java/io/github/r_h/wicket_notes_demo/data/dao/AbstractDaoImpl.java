package io.github.r_h.wicket_notes_demo.data.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

/**
 * Implementation of the DAO.
 * 
 * @param <T>
 *            type of the object
 */
@Repository
public abstract class AbstractDaoImpl<T extends Serializable> implements Dao<T> {

	private final Class<T> type;

	@PersistenceContext
   	private EntityManager entityManager;

	public AbstractDaoImpl(Class<T> type) {
		this.type = type;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T load(Serializable id) {
		return entityManager.find(type, id);
	}

	@TransactionAttribute
	public T save(T object) {
		return entityManager.merge(object);
	}

	@TransactionAttribute
	public void delete(T object) {
		T merged = save(object);
		entityManager.remove(merged);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Set<T> findAll() {
				TypedQuery<T> query = entityManager.createQuery(
						"select x from " + type.getName() + " x", type);
				return new HashSet<T>(query.getResultList());
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public long countAll() {				
				TypedQuery<Long> query = entityManager.createQuery("select count(x) from "
						+ type.getName() + " x", Long.class);
				return (query.getSingleResult()).intValue();
	}

	protected EntityManager getEntityManager(){
		return entityManager;
	}

}
