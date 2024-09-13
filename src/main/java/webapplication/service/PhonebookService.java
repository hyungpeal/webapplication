package webapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapplication.entity.Phonebook;
import webapplication.respository.PhonebookDAO;

@Service
public class PhonebookService {

	@Autowired
	PhonebookDAO dao;
	
	public void save(Phonebook pb) {

		int result = dao.insert(pb);
		
		if(result == 1) System.out.println("저장 성공");
		else System.out.println("저장 실패");
		
	}

	public List<Phonebook> findAll() {
		return dao.selectAll();
	}

	public Phonebook findById(int id) {
		return dao.selectById(id);
	}

	public int update(Phonebook pb) {
		return dao.update(pb);
	}

	public int delete(int id) {
		return dao.delete(id);
	}

	public List<Phonebook> search(String _search) {
		return dao.search(_search);
	}

	
	
}
