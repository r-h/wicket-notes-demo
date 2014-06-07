package io.github.r_h.wicket_notes_demo.data;

import io.github.r_h.wicket_notes_demo.data.note.Note;
import io.github.r_h.wicket_notes_demo.data.note.NoteDao;
import io.github.r_h.wicket_notes_demo.data.user.User;
import io.github.r_h.wicket_notes_demo.data.user.UserDao;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Initialize the database.
 */
@Component
public class DataInitialiser implements InitializingBean {

	@Autowired
	private UserDao userDAO;

	protected void initUsers() {
		User test = new User();
		test.setName("test");
		test.setPassword("test");

		userDAO.save(test);

		User admin = new User();
		admin.setName("admin");
		admin.setPassword("admin");

		userDAO.save(admin);
	}

	@Autowired
	private NoteDao noteDAO;

	protected void initNotes() {
		User test = userDAO.findByUsername("test");
		User admin = userDAO.findByUsername("admin");

		Note n1 = new Note();
		n1.setHeader("First note");
		n1.setText("As I said, the very first note");
		n1.setTimestamp(new Date());
		n1.setUser(test);

		noteDAO.save(n1);

		Note n2 = new Note();
		n2.setHeader("Second note");
		n2.setText("Give me notes");
		n2.setTimestamp(new Date());
		n2.setUser(admin);

		noteDAO.save(n2);

		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Note n = new Note();
			n.setHeader("Dummy note " + i);
			n.setText("Note " + i);
			long timestamp = r.nextLong();
			if (timestamp < 0) {
				timestamp = -timestamp;
			}
			timestamp = timestamp % System.currentTimeMillis();
			n.setTimestamp(new Date(timestamp));
			n.setUser(test);
			noteDAO.save(n);
		}
	}

	public void afterPropertiesSet() throws Exception {
		initUsers();
		initNotes();

	}
}
