package tw.com.softleader.sample.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;

public class BookDao implements GenericDao<Book> {

	private final String DB_DRIVER = "org.postgresql.Driver";
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

	@Override
	public Book findOne(Long id) {
		Book book = new Book();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();

			String sqlCmd = "select * from book where id=" + id;

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {

				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));

			}
			rs.close();

			stmt.close();

			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public Collection<Book> findAll() {
		Collection<Book> books = new ArrayList<Book>();

		try {
			Class.forName(DB_DRIVER);

			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM BOOK";

			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				Book book = new Book();
				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));

				books.add(book);

			}

			rs.close();

			stmt.close();

			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;

	}

	@Override
	public void insert(Book entity) {
		try {
			Class.forName(DB_DRIVER);

			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			String sqlCmd = "insert into book(name,type) values (?,?)";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getType());

			pstmt.executeUpdate();

			pstmt.close();

			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Book entity) {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();

			String sqlCmd = "update book set name='" + entity.getName() + "',type='" + entity.getType() + "' where id='"
					+ entity.getId() + "'";

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();

			String sqlCmd = "delete from book where id=" + id;

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
