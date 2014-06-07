package io.github.r_h.wicket_notes_demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class FormattedDateModel extends Model<String> {

	private final IModel<Date> model;
	private final DateFormat format;

	public FormattedDateModel(IModel<Date> model, DateFormat format) {
		this.model = model;
		this.format = format;
	}

	@Override
	public String getObject() {
		return format.format(model.getObject());
	}

	@Override
	public void setObject(String s) {
		try {
			model.setObject(format.parse(s));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
