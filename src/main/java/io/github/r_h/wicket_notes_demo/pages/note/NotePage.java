package io.github.r_h.wicket_notes_demo.pages.note;

import io.github.r_h.wicket_notes_demo.AbstractDemoPage;
import io.github.r_h.wicket_notes_demo.data.note.Note;
import io.github.r_h.wicket_notes_demo.data.note.NoteDao;
import io.github.r_h.wicket_notes_demo.util.DefaultDateTimeConverter;

import java.util.Date;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import org.odlabs.wiquery.ui.button.ButtonBehavior;
import org.odlabs.wiquery.ui.datepicker.DatePicker;

@SuppressWarnings("serial")
public class NotePage extends AbstractDemoPage {

	@SpringBean
	private NoteDao dao;

	public NotePage() {
		this(new Note());
	}

	public NotePage(Note note) {
		note.setUser(getSession().getUser());

		final Form<Note> form = new Form<Note>("noteForm",
				new CompoundPropertyModel<Note>(note));
		form.add(new TextField<String>(Note.HEADER));
		form.add(new TextArea<String>(Note.TEXT));
		DatePicker<Date> timestamp = new DatePicker<Date>(Note.TIMESTAMP,
				Date.class) {
			@Override
			public IConverter getConverter(Class<?> type) {
				return new DefaultDateTimeConverter();
			}
		};
		timestamp.setDateFormat(DefaultDateTimeConverter.PICKER_PATTERN);
		form.add(timestamp);

		Button cancelButton = new Button("cancelButton") {
			@Override
			public void onSubmit() {
				setResponsePage(NotesPage.class);
			};
		};
		cancelButton.add(new ButtonBehavior());
		cancelButton.setDefaultFormProcessing(false);
		form.add(cancelButton);

		Button saveButton = new Button("saveButton") {
			@Override
			public void onSubmit() {
				Note note = form.getModel().getObject();
				dao.save(note);
				setResponsePage(NotesPage.class);
			}
		};
		saveButton.add(new ButtonBehavior());
		form.add(saveButton);
		add(form);
	}
}
