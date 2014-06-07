package io.github.r_h.wicket_notes_demo.data.user;

import io.github.r_h.wicket_notes_demo.data.dao.AbstractDaoImpl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Dao for a User.
 */
@Component
public class UserDAOImpl extends AbstractDaoImpl<User> implements UserDao {

	public UserDAOImpl() {
		super(User.class);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public User findByUsername(final String username) {
		return getJpaTemplate().execute(new JpaCallback<User>() {
			public User doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<User> query = em.createNamedQuery(
						"User.findByUsername", User.class);
				query.setParameter("name", username);
				try {
					return query.getSingleResult();
				} catch (NoResultException e) {
					return null;
				}
			}
		});
	}
}
