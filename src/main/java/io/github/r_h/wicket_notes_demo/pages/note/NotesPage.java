package io.github.r_h.wicket_notes_demo.pages.note;

import io.github.r_h.wicket_notes_demo.AbstractDemoPage;
import io.github.r_h.wicket_notes_demo.data.note.Note;
import io.github.r_h.wicket_notes_demo.data.note.NoteDao;
import io.github.r_h.wicket_notes_demo.data.user.User;
import io.github.r_h.wicket_notes_demo.util.AreYouSureButton;
import io.github.r_h.wicket_notes_demo.util.DefaultDateTimeConverter;
import io.github.r_h.wicket_notes_demo.util.FormattedDateModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.component.event.OnSelectRowAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OndblClickRowAjaxEvent;
import com.wiquery.plugins.jqgrid.model.GridColumnModel;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.ICellPopulator;
import com.wiquery.plugins.jqgrid.model.PropertyPopulator;
import com.wiquery.plugins.jqgrid.model.SortOrder;

@SuppressWarnings("serial")
public class NotesPage extends AbstractDemoPage {

	@SpringBean
	private NoteDao dao;

	private WebMarkupContainer gridContainer;
	private AreYouSureButton deleteButton;

	private final Set<Note> selectedNotes = new HashSet<Note>();

	public NotesPage() {

		deleteButton = new AreYouSureButton("deleteButton", "Delete",
				"Do you really want to delete?") {

			@Override
			protected void onConfirm(AjaxRequestTarget target) {
				doDelete(target);
			}

			@Override
			protected void onCancel(AjaxRequestTarget target) {
			}

			@Override
			public boolean isEnabled() {
				return !selectedNotes.isEmpty();
			}

		};
		deleteButton.setOutputMarkupId(true);
		add(deleteButton);

		final GridModel<Note> model = new GridModel<Note>(Note.class);
		model.setCaption("Notes");
		model.addColumnModel(new GridColumnModel<Note>(Note.HEADER,
				Note.HEADER, new Model<String>("Header"), 500));
		model.addColumnModel(new GridColumnModel<Note>(Note.USER + "."
				+ User.NAME, Note.USER + "." + User.NAME, new Model<String>(
				"User"), 200));
		GridColumnModel<Note> timestampColumn = new GridColumnModel<Note>(
				Note.TIMESTAMP, Note.TIMESTAMP, new Model<String>("Timestamp"),
				200) {
			@Override
			public ICellPopulator<Note> getCellPopulator() {
				return new PropertyPopulator<Note>(getPropertyPath()) {
					@Override
					public void populateItem(
							Item<ICellPopulator<Note>> cellItem,
							String componentId, int row, int col,
							IModel<Note> rowModel) {
						cellItem.add(new Label(componentId,
								new FormattedDateModel(new PropertyModel<Date>(
										rowModel, getPropertyPath()),
										new DefaultDateTimeConverter()
												.getDateFormat(null))));
					}
				};
			}
		};
		timestampColumn.setInitialSort(true);
		model.addColumnModel(timestampColumn);
		model.setSortOrder(SortOrder.desc);

		model.setMultiselect(true);

		Grid<Note> grid = new Grid<Note>("notes", model,
				new SortableNotesDataProvider(dao));
		grid.addEvent(new OndblClickRowAjaxEvent<Note>() {
			@Override
			protected void ondblClickRow(AjaxRequestTarget target, int row,
					int col, IModel<Note> rowModel) {
				setResponsePage(new NotePage(rowModel.getObject()));
			}
		});
		grid.addEvent(new OnSelectRowAjaxEvent<Note>() {
			@Override
			protected void onSelectRow(AjaxRequestTarget target, int row,
					IModel<Note> rowModel, boolean selected) {
				if (selected) {
					selectedNotes.add(rowModel.getObject());
				} else {
					selectedNotes.remove(rowModel.getObject());
				}
				target.addComponent(deleteButton);
			};
		});

		gridContainer = new WebMarkupContainer("container");
		gridContainer.setOutputMarkupId(true);
		gridContainer.add(grid);
		add(gridContainer);

	}

	protected void doDelete(AjaxRequestTarget target) {
		for (Note n : selectedNotes) {
			dao.delete(n);
		}
		selectedNotes.clear();
		target.addComponent(gridContainer);
		target.addComponent(deleteButton);
	}
}
