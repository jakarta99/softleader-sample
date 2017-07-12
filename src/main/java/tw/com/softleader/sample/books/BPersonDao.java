package tw.com.softleader.sample.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class BPersonDao implements GenericDao<BPerson> {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public BPerson findOne(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {

			connection = ds.getConnection();
			String sqlCmd = "select * from bperson where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			String sqlCmdb = "select * from book where p_id=?";
			PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
			pstmtb.setLong(1, id);
			ResultSet rsb = pstmtb.executeQuery();
			Collection<Book> books = null;

			if (rs.next()) {
				BPerson person = new BPerson();
				books = new ArrayList<Book>();
				person.setId(rs.getLong("id"));
				person.setName(rs.getString("name"));
				person.setIdno(rs.getString("idno"));
				while (rsb.next()) {
					Book book = new Book();
					book.setId(rsb.getLong("id"));
					book.setName(rsb.getString("name"));
					book.setType(rsb.getString("type"));
					book.setP_id(id);
					books.add(book);
				}

				person.setBooks(books);

				return person;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return null;
	}

	@Override
	public Collection<BPerson> findAll() {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Collection<BPerson> personS = new ArrayList<BPerson>();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			String sqlCmd = "select * from bperson";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BPersonDao bpersondao = new BPersonDao();
				BPerson bperson = bpersondao.findOne(rs.getLong("id"));
				personS.add(bperson);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return personS;
	}

	@Override
	public void insert(BPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			String sqlCmd = "insert into bperson(name,idno) values(?,?);";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getIdno());
			int num1 = pstmt.executeUpdate();
			log.debug("insert sql : " + sqlCmd);
			log.debug("insert1 num : " + num1);

			ResultSet keySet = pstmt.getGeneratedKeys();
			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				entity.setId(generatedId);
			}

			Iterator<Book> books = entity.getBooks().iterator();
			log.debug("aaa: " + entity.getBooks().size());

			for (int i = 0; i < entity.getBooks().size(); i++) {
				Book book = books.next();
				String sqlCmdb = "insert into book(name,type,p_id) values(?,?,?);";
				PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
				pstmtb.setString(1, book.getName());
				pstmtb.setString(2, book.getType());
				pstmtb.setLong(3, entity.getId());
				pstmtb.executeUpdate();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	@Override
	public void update(BPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			String sqlCmd = "delete from book where p_id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, entity.getId());
			pstmt.executeUpdate();

			Iterator<Book> books = entity.getBooks().iterator();

			for (int i = 0; i < entity.getBooks().size(); i++) {
				Book book = books.next();
				String sqlCmdb = "insert into book(name,type,p_id) values(?,?,?);";
				PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
				pstmtb.setString(1, book.getName());
				pstmtb.setString(2, book.getType());
				pstmtb.setLong(3, entity.getId());
				pstmtb.executeUpdate();
			}

			log.debug("update book: " + entity.getBooks().iterator().next().getId());
			log.debug("update id: " + entity.getId());
			log.debug("update sql: " + sqlCmd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			String sqlCmdb = "delete from book where p_id=?";
			PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
			pstmtb.setLong(1, id);
			pstmtb.executeUpdate();

			String sqlCmd = "delete from bperson where id=?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

}
