package io.github.r_h.wicket_notes_demo.data.user;

import io.github.r_h.wicket_notes_demo.data.dao.AbstractDaoImpl;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
		
				TypedQuery<User> query = getEntityManager().createNamedQuery(
						"User.findByUsername", User.class);
				query.setParameter("name", username);
				try {
					return query.getSingleResult();
				} catch (NoResultException e) {
					return null;
				}
	
	}
}
