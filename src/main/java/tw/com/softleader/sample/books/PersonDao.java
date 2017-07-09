package tw.com.softleader.sample.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.GenericDao;

public class PersonDao implements GenericDao<Person> {

	private Logger log = Logger.getLogger(this.getClass());
	private final String URL = "jdbc:postgresql://localhost:5432/testdb";
	private final String acc = "postgres";
	private final String password = "postgres";

	@Override
	public Person findOne(Long id) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "select p.p_id,personname,idno,id,bookname,booktype from book b join person p on b.p_id = p.p_id where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);

			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);
			log.fatal("5:" + sqlCmd);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Person person = new Person();
				person.setP_id(rs.getLong("p_id"));
				person.setName(rs.getString("personname"));
				person.setIdno(rs.getString("idno"));
				person.setId(rs.getLong("id"));
				person.setBookname(rs.getString("bookname"));
				person.setBooktype(rs.getString("booktype"));

				return person;
			}

			rs.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<Person> findAll() {
		Collection<Person> personS = new ArrayList<Person>();
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "select p.p_id,personname,idno,id,bookname,booktype from book b join person p on b.p_id = p.p_id where p.p_id=1";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Person person = new Person();
				person.setP_id(rs.getLong("p_id"));
				person.setName(rs.getString("personname"));
				person.setIdno(rs.getString("idno"));
				person.setId(rs.getLong("id"));
				person.setBookname(rs.getString("bookname"));
				person.setBooktype(rs.getString("booktype"));

				personS.add(person);
			}
			rs.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personS;
	}

	@Override
	public void insert(Person entity) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "insert into book(bookname,booktype,p_id) values(?,?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getBookname());
			pstmt.setString(2, entity.getBooktype());
			pstmt.setLong(3, entity.getP_id());
			pstmt.executeUpdate();


			ResultSet keySet = pstmt.getGeneratedKeys();
			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				entity.setId(generatedId);
			}
			keySet.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Person entity) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "update book set bookname=?,booktype=?,p_id=? where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setString(1, entity.getBookname());
			pstmt.setString(2, entity.getBooktype());
			pstmt.setLong(3, entity.getP_id());
			pstmt.setLong(4, entity.getId());
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "delete from book where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
