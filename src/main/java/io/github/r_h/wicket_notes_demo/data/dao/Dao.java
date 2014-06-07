package io.github.r_h.wicket_notes_demo.data.dao;

import java.io.Serializable;
import java.util.Set;

/**
 * Generic Dao interface.
 * 
 * @param <T>
 *            type read/written
 */
public interface Dao<T extends Serializable> {
	public T load(Serializable id);

	public T save(T o);

	public void delete(T o);

	public Set<T> findAll();

	public long countAll();

}