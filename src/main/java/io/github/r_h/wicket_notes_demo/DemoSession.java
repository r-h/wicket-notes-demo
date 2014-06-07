package io.github.r_h.wicket_notes_demo;

import io.github.r_h.wicket_notes_demo.data.user.User;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

@SuppressWarnings("serial")
public class DemoSession extends WebSession {

	public DemoSession(Request request) {
		super(request);
	}

	/**
	 * The authenticated user.
	 */
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if (user != null) {
			setStyle(user.getName());
		} else {
			setStyle(null);
		}
	}

	public boolean isLoggedIn() {
		return user != null;
	}

}
