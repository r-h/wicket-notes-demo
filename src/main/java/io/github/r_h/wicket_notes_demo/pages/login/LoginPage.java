package io.github.r_h.wicket_notes_demo.pages.login;

import io.github.r_h.wicket_notes_demo.AbstractDemoPage;
import io.github.r_h.wicket_notes_demo.data.user.User;
import io.github.r_h.wicket_notes_demo.data.user.UserDao;
import io.github.r_h.wicket_notes_demo.pages.note.NotesPage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

@SuppressWarnings("serial")
public class LoginPage extends AbstractDemoPage {

	private final User user = new User();

	@SpringBean
	private UserDao userDAO;

	public LoginPage() {
		final Form<User> form = new Form<User>("loginForm",
				new CompoundPropertyModel<User>(user));
		TextField<String> nameField = new TextField<String>(User.NAME);
		form.add(nameField);
		PasswordTextField passwordField = new PasswordTextField(User.PASSWORD);
		form.add(passwordField);

		Button submit = new Button("loginButton") {
			@Override
			public void onSubmit() {
				User dbUser = userDAO.findByUsername(user.getName());
				if (dbUser != null
						&& dbUser.getPassword().equals(user.getPassword())) {
					LoginPage.this.getSession().setUser(dbUser);
					setResponsePage(NotesPage.class);
				} else {
					form.error("Invalid login");
				}
			};
		};
		submit.add(new ButtonBehavior());
		form.add(submit);
		add(form);
	}

}
