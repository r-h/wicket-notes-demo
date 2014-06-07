package io.github.r_h.wicket_notes_demo.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConfirmationAnswer implements Serializable {
	
	private boolean yes;

	public ConfirmationAnswer(boolean yes) {
		this.yes = yes;
	}

	public boolean isYes() {
		return yes;
	}

	public void setYes(boolean yes) {
		this.yes = yes;
	}
}
