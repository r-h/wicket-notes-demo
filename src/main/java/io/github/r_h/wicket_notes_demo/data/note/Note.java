package io.github.r_h.wicket_notes_demo.data.note;

import io.github.r_h.wicket_notes_demo.data.user.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The note entity.
 */
@Entity
@SuppressWarnings("serial")
public class Note implements Serializable {

	private long id;
	private String header;
	private String text;
	private Date timestamp = new Date();

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static final String HEADER = "header";

	@NotNull
	@Size(min = 1, max = 100)
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public static final String TEXT = "text";

	@NotNull
	@Size(min = 1, max = 1000)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static final String TIMESTAMP = "timestamp";

	@NotNull
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	private User user;

	public static final String USER = "user";

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
