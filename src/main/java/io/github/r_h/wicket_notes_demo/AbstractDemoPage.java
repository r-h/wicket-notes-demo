package io.github.r_h.wicket_notes_demo;

import io.github.r_h.wicket_notes_demo.pages.note.NotePage;
import io.github.r_h.wicket_notes_demo.pages.note.NotesPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

@SuppressWarnings("serial")
public class AbstractDemoPage extends WebPage {

	public AbstractDemoPage() {
		add(new BookmarkablePageLink<String>("list", NotesPage.class) {
			@Override
			public boolean isVisible() {
				return AbstractDemoPage.this.getSession().isLoggedIn();
			}
		});
		add(new BookmarkablePageLink<String>("newNote", NotePage.class) {
			@Override
			public boolean isVisible() {
				return AbstractDemoPage.this.getSession().isLoggedIn();
			}
		});
		add(new Label("user", new PropertyModel<String>(this,
				"session.user.name")) {
			@Override
			public boolean isVisible() {
				return AbstractDemoPage.this.getSession().isLoggedIn();
			}
		});
		add(new FeedbackPanel("feedback"));
	}

	@Override
	public DemoSession getSession() {
		return (DemoSession) super.getSession();
	}

}
