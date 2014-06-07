package io.github.r_h.wicket_notes_demo.util;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.button.ButtonBehavior;

@SuppressWarnings("serial")
public abstract class AreYouSureButton extends Panel {

	public AreYouSureButton(String id, String buttonName,
			String modalMessageText) {
		super(id);
		final ConfirmationAnswer answer = new ConfirmationAnswer(false);

		final ModalWindow confirmModal = new ModalWindow("modal");
		confirmModal.setCookieName(id);
		confirmModal.setContent(new YesNoPanel(confirmModal.getContentId(),
				modalMessageText, confirmModal, answer));
		confirmModal
				.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

					public void onClose(AjaxRequestTarget target) {
						if (answer.isYes()) {
							onConfirm(target);
						} else {
							onCancel(target);
						}
					}
				});

		Form<Object> form = new Form<Object>("confirmForm");
		AjaxButton confirmButton = new AjaxButton("confirmButton",
				new Model<String>(buttonName)) {
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				answer.setYes(false);
				confirmModal.show(target);
			}
		};
		confirmButton.add(new ButtonBehavior());

		form.add(confirmButton);
		form.add(confirmModal);
		add(form);
	}

	protected abstract void onConfirm(AjaxRequestTarget target);

	protected abstract void onCancel(AjaxRequestTarget target);

}