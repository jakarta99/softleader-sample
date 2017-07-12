package tw.com.softleader.sample.books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class BCompanyDao implements GenericDao<BCompany> {

	@SuppressWarnings("null")
	@Override
	public BCompany findOne(Long id) {
		javax.sql.DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		BCompany bcompany = null;
		Collection<BPerson> bpersons = new ArrayList<BPerson>();
		Collection<Book> books = new ArrayList<Book>();
		BPerson bperson = null;
		try {
			//先找BCompany
			connection = ds.getConnection();
			String sqlCmdc = "select * from BCompany where id = ?";
			PreparedStatement pstmtc = connection.prepareStatement(sqlCmdc);
			pstmtc.setLong(1, id);
			ResultSet rsc = pstmtc.executeQuery();
			//再找BPerson的相同_id
			String sqlCmdp = "select * from bperson where c_id = ?";
			PreparedStatement pstmtp = connection.prepareStatement(sqlCmdp);
			pstmtp.setLong(1, id);
			ResultSet rsp = pstmtp.executeQuery();
			//如果有該公司
			if (rsc.next()) {
				bcompany.setId(rsc.getLong("id"));
				bcompany.setName(rsc.getString("name"));
				//如果該公司有多名員工
				while (rsp.next()) {
					bperson = new BPerson();
					bperson.setC_id(rsp.getLong("c_id"));
					bperson.setName(rsp.getString("name"));
					bperson.setIdno(rsp.getString("idno"));
					bperson.setId(rsp.getLong("id"));
					String sqlCmdb = "select * from book where p_id = ?";
					PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
					pstmtb.setLong(1, rsp.getLong("id"));
					ResultSet rsb = pstmtb.executeQuery();
					//如果該員工有多本書
					while (rsb.next()) {
						Book book = new Book();
						book.setId(rsb.getLong("id"));
						book.setName(rsb.getString("name"));
						book.setType(rsb.getString("type"));
						book.setP_id(rsb.getLong("p_id"));
						//把很多本書蒐集起來
						books.add(book);
					}
					//把書丟回員工
					bperson.setBooks(books);
					bpersons.add(bperson);
				}
				//把員工丟回公司
				bcompany.setBpersons(bpersons);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return bcompany;
	}

	@SuppressWarnings("null")
	@Override
	public Collection<BCompany> findAll() {
		javax.sql.DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		Collection<BCompany> companies = null;
		try {
			connection = ds.getConnection();
			String sqlCmdc = "select * from BCompany";
			PreparedStatement pstmtc = connection.prepareStatement(sqlCmdc);
			ResultSet rsc = pstmtc.executeQuery();
			//用findone的方法 做迴圈 將全部公司蒐集起來
			while (rsc.next()) {
				BCompanyDao bcompanydao = new BCompanyDao();
				companies.add(bcompanydao.findOne(rsc.getLong("id")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return companies;
	}

	@Override
	public void insert(BCompany entity) {
		javax.sql.DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		Iterator<BPerson> bperson = entity.getBpersons().iterator();
		Iterator<Book> book = entity.getBpersons().iterator().next().getBooks().iterator();
		try {
			//先將公司資料丟進BCompany table裡 並回傳公司id
			connection = ds.getConnection();
			String sqlCmdc = "insert into BCompany(name) values(?)";
			PreparedStatement pstmtc = connection.prepareStatement(sqlCmdc, Statement.RETURN_GENERATED_KEYS);
			pstmtc.setString(1, entity.getName());
			pstmtc.executeUpdate();
			//回傳且設定公司的id值 
			ResultSet keySetc = pstmtc.getGeneratedKeys();
			if (keySetc.next()) {
				Long generatedId = keySetc.getLong("id");
				entity.setId(generatedId);
			}//將員工資料丟進BPerson table裡 並回傳員工id
			while (bperson.hasNext()) {
				BPerson bpersoni = bperson.next();
				String sqlCmdp = "insert into bperson(name,idno,c_id) values(?,?,?)";
				PreparedStatement pstmtp = connection.prepareStatement(sqlCmdp, Statement.RETURN_GENERATED_KEYS);
				pstmtp.setString(1, bpersoni.getName());
				pstmtp.setString(2, bpersoni.getIdno());
				pstmtp.setLong(3, bpersoni.getC_id());
				pstmtp.executeUpdate();
				//回傳且設定員工id
				ResultSet keySetp = pstmtc.getGeneratedKeys();
				if (keySetp.next()) {
					Long generatedId = keySetp.getLong("id");
					bpersoni.setId(generatedId);
				}
			}
			//將員工的書丟進Book table裡
			while (book.hasNext()) {
				Book booki = book.next();
				String sqlCmdb = "insert into book(name,type,p_id) values(?,?,?)";
				PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
				pstmtb.setString(1, booki.getName());
				pstmtb.setString(2, booki.getType());
				pstmtb.setLong(3, booki.getP_id());
				pstmtb.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(BCompany entity) {
		javax.sql.DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			BCompanyDao bcompanydao = new BCompanyDao();
			//刪除舊資料
			bcompanydao.delete(entity.getId());
			//插入新資料
			bcompanydao.insert(entity);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Long id) {
		javax.sql.DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection connection = null;
		try {
			connection = ds.getConnection();
			// 先殺Book
			BCompanyDao bcompanydao = new BCompanyDao();
			Iterator<BPerson> bperson = bcompanydao.findOne(id).getBpersons().iterator();
			Iterator<Book> book = bperson.next().getBooks().iterator();

			while (bperson.hasNext()) {
				while (book.hasNext()) {
					Book booki = book.next();
					Long b_id = booki.getP_id();
					String sqlCmdb = "delete from book where p_id = ?";
					PreparedStatement pstmtb = connection.prepareStatement(sqlCmdb);
					pstmtb.setLong(1, b_id);
				}
			}

			// 再殺 BPerson
			String sqlCmdp = "delete from Bperson where c_id = ?";
			PreparedStatement pstmtp = connection.prepareStatement(sqlCmdp);
			pstmtp.setLong(1, id);
			pstmtp.executeUpdate();

			// 最後殺BCompany
			String sqlCmdc = "delete from BCompany where id = ?";
			PreparedStatement pstmtc = connection.prepareStatement(sqlCmdc);
			pstmtc.setLong(1, id);
			pstmtc.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
