package webapplication.respository;

import java.util.List;

import webapplication.entity.Phonebook;

public interface PhonebookDAO {
	
	// 전화번호 저장
	public int insert(Phonebook pb);
	
	// 전화번호 전체 리스트
	public List<Phonebook> selectAll();
	
	// 전화번호 검색 리스트
	public List<Phonebook> search(String _search);
	
	// id에 해당하는 전화번호 객체 데이터 가져오기
	public Phonebook selectById(int id);
	
	// 전화번호부 수정
	public int update(Phonebook pb);
	
	// 전화번호부 삭제
	public int delete(int id);

}
