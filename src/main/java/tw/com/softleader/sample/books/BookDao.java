package tw.com.softleader.sample.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class BookDao implements GenericDao<Book> {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Book findOne(Long id) {

		try {
			DataSource ds=DataSourceUtil.getInstance().getDataSource();
			Connection connection=ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "select * from book where id=" + id;

			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);
			log.fatal("5:" + sqlCmd);

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {
				Book book = new Book();
				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setType(rs.getString("type"));

				return book;

			}
			rs.close();

			stmt.close();

			connection.close();
		}  catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<Book> findAll() {
		Collection<Book> books = new ArrayList<Book>();

		try {
			DataSource ds=DataSourceUtil.getInstance().getDataSource();
			Connection connection=ds.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;

	}

	@Override
	public void insert(Book entity) {
		try {
			DataSource ds=DataSourceUtil.getInstance().getDataSource();
			Connection connection=ds.getConnection();
			String sqlCmd = "insert into book(name,type) values (?,?)";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getType());

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
			e.printStackTrace();
		}

	}

	@Override
	public void update(Book entity) {
		try {
			DataSource ds=DataSourceUtil.getInstance().getDataSource();
			Connection connection=ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "update book set name='" + entity.getName() + "',type='" + entity.getType() + "' where id='"
					+ entity.getId() + "'";

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			DataSource ds=DataSourceUtil.getInstance().getDataSource();
			Connection connection=ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "delete from book where id=" + id;

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
