package io.github.r_h.wicket_notes_demo.data.user;

import io.github.r_h.wicket_notes_demo.data.dao.Dao;

/**
 * DAO for {@link User}.
 */
public interface UserDao extends Dao<User> {

	public User findByUsername(String username);

}
