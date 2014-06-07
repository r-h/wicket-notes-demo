package io.github.r_h.wicket_notes_demo.util;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

@SuppressWarnings("serial")
public class YesNoPanel extends Panel {

	public YesNoPanel(String id, String message, final ModalWindow modalWindow,
			final ConfirmationAnswer answer) {
		super(id);

		Form<Object> form = new Form<Object>("yesNoForm");

		MultiLineLabel messageLabel = new MultiLineLabel("message", message);
		form.add(messageLabel);
		modalWindow.setTitle("Please confirm");
		modalWindow.setInitialHeight(200);
		modalWindow.setInitialWidth(350);

		AjaxButton yesButton = new AjaxButton("yesButton", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					answer.setYes(true);
					modalWindow.close(target);
				}
			}
		};
		yesButton.add(new ButtonBehavior());
		form.add(yesButton);

		AjaxButton noButton = new AjaxButton("noButton", form) {

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (target != null) {
					answer.setYes(false);
					modalWindow.close(target);
				}
			}
		};
		noButton.add(new ButtonBehavior());
		form.add(noButton);

		add(form);
	}

}