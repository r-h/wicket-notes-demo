package io.github.r_h.wicket_notes_demo;

import io.github.r_h.wicket_notes_demo.pages.login.LoginPage;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.application.IComponentInitializationListener;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.zenika.wicket.contrib.jsr303validators.JSR303FormValidator;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    
	/**
	 * Create the spring injector. Overwritten in unit tests.
	 * 
	 * @return the injector
	 */
	protected SpringComponentInjector createSpringInjector() {
		return new SpringComponentInjector(this);
	}

	@Override
	protected void init() {
		super.init();
		getMarkupSettings().setStripWicketTags(true);

		addComponentInstantiationListener(createSpringInjector());
		addComponentInitializationListener(new IComponentInitializationListener() {
			public void onInitialize(Component component) {
				if (component instanceof Form<?>
						&& ((Form<?>) component).getModel() instanceof CompoundPropertyModel<?>) {
					((Form<?>) component).add(new JSR303FormValidator());
				}
			}
		});
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new DemoSession(request);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

}
