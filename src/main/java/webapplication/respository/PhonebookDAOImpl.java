package webapplication.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import webapplication.entity.Phonebook;

@Repository
public class PhonebookDAOImpl implements PhonebookDAO {

	Connection conn;
	
	public PhonebookDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"system",
				"1234"
			);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	
	@Override
	public int insert(Phonebook pb) {
		try {
			String sql="insert into phonebook values(phonebook_id_seq.NEXTVAL, ?, ?, ?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,pb.getName());
			ps.setString(2,pb.getHp());
			ps.setString(3,pb.getMemo());
			int result=ps.executeUpdate();
			ps.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Phonebook> selectAll() {
		try {
			String sql="select * from Phonebook";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Phonebook> pb = new ArrayList<Phonebook>();
			while(rs.next()) {
				pb.add(new Phonebook(
						rs.getInt("id")
						,rs.getString("name")
						, rs.getString("hp")
						, rs.getString("memo"))
				);
			}

			ps.close();
			rs.close();
			return pb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Phonebook> search(String _search) {
		try {
			String sql="select * from Phonebook where hp like '%' || ? || '%' OR name like '%' || ? || '%'";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, _search);
			ps.setString(2, _search);
			ResultSet rs = ps.executeQuery();
			List<Phonebook> pb = new ArrayList<Phonebook>();
			while(rs.next()) {
				pb.add(new Phonebook(
						rs.getInt("id")
						,rs.getString("name")
						, rs.getString("hp")
						, rs.getString("memo"))
				);
			}
			rs.close();
			ps.close();
			return pb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Phonebook selectById(int id) {
		try {
			String sql="select * from Phonebook where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Phonebook pb = null;
			if(rs.next()) {
				pb = new Phonebook(
						rs.getInt("id")
						,rs.getString("name")
						, rs.getString("hp")
						, rs.getString("memo")
				);
			}
			rs.close();
			ps.close();
			return pb;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Phonebook pb) {
		try {
			String sql="update phonebook set name=?, hp=?, memo=? where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, pb.getName());
			ps.setString(2, pb.getHp());
			ps.setString(3, pb.getMemo());
			ps.setInt(4, pb.getId());
			int result = ps.executeUpdate();
			

			ps.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int id) {
		try {
			String sql="delete from phonebook where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			int result = ps.executeUpdate();

			ps.close();
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
