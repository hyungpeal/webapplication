package webapplication;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import webapplication.entity.Phonebook;
import webapplication.respository.PhonebookDAO;

public class DBTest {
	
	@Autowired
	PhonebookDAO dao;

	@Test
	public void test() {
		Phonebook pb = new Phonebook(0, "asdf", "1234", "memo");
		dao.insert(pb);
	}

}
