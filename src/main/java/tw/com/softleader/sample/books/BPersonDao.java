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

public class BPersonDao implements GenericDao<BPerson> {

	private Logger log = Logger.getLogger(this.getClass());
	private final String URL = "jdbc:postgresql://localhost:5432/testdb";
	private final String acc = "postgres";
	private final String password = "postgres";
	private BookDao bookDao = new BookDao();

	@Override
	public BPerson findOne(Long id) {
		try {

			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "select * from bperson join book on book = book.id where book.id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BPerson person = new BPerson();
				Collection<Book> books = new ArrayList<Book>();
				person.setId(rs.getLong("id"));
				person.setName(rs.getString("name"));
				person.setIdno(rs.getString("idno"));

				books.add(bookDao.findOne(rs.getLong("book")));

				person.setBooks(books);

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
	public Collection<BPerson> findAll() {
		Collection<BPerson> personS = new ArrayList<BPerson>();
		Collection<Book> books = new ArrayList<Book>();
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "select * from bperson join book on book = book.id";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BPerson person = new BPerson();
				person.setId(rs.getLong("id"));
				person.setName(rs.getString("name"));
				person.setIdno(rs.getString("idno"));

				books.add(bookDao.findOne(rs.getLong("book")));
				person.setBooks(books);

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
	public void insert(BPerson entity) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "insert into bperson(name,idno,book) values(?,?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getIdno());
			pstmt.setLong(3, entity.getBooks().iterator().next().getId());
			pstmt.executeUpdate();
			
			log.debug("insert sql : " + sqlCmd);

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
	public void update(BPerson entity) {
		try {
			Connection connection = DriverManager.getConnection(URL, acc, password);
			String sqlCmd = "update bperson set book=? where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, entity.getBooks().iterator().next().getId());
			pstmt.setLong(2, entity.getId());
			
			log.debug("update book: "+ entity.getBooks().iterator().next().getId());
			log.debug("update id: "+ entity.getId());
			log.debug("update sql: "+ sqlCmd);

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
			String sqlCmd = "delete from bperson where id=?";
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
