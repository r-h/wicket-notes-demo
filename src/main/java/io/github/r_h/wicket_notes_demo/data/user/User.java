package io.github.r_h.wicket_notes_demo.data.user;

import io.github.r_h.wicket_notes_demo.data.note.Note;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The user entity.
 */
@Entity
@NamedQuery(name = "User.findByUsername", query = "select u from User u where upper(u.name) = upper(:name)")
@SuppressWarnings("serial")
@Table(name="userEntity")
public class User implements Serializable {

	private long id;
	private String name;
	private String password;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static final String NAME = "name";

	@NotNull
	@Size(min = 1, max = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static final String PASSWORD = "password";

	@NotNull
	@Size(min = 1, max = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Set<Note> notes;

	public static final String NOTES = "notes";

	@OneToMany(mappedBy = "user")
	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

}
