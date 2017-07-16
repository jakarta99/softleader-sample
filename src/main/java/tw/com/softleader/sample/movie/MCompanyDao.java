package tw.com.softleader.sample.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class MCompanyDao implements GenericDao<MCompany> {

	private Logger log = Logger.getLogger(this.getClass());

	Collection<MCompany> mCompanys = new ArrayList<MCompany>();// 準備好大家要用的東西
	MCompany mCompany = new MCompany();
	Collection<MPerson> mPersons = new ArrayList<MPerson>();
	MPerson mPerson = new MPerson();
	Collection<Movie> movies = new ArrayList<Movie>();
	Movie movie = new Movie();
	DataSource ds = DataSourceUtil.getInstance().getDataSource();// 連線
	Connection connection = null;

	@Override
	public MCompany findOne(Long id) {
		try {
			connection = ds.getConnection();
			String sqlcmdc = "select * from MCompany where id = ?";// 先把SQL指令下好
			String sqlcmdp = "select * from MPerson where cId = ?";
			String sqlcmdm = "select * from Movie where mId = ?";

			PreparedStatement prstmtc = connection.prepareStatement(sqlcmdc);// 動態指令使用PreparedStatement
			PreparedStatement prstmtp = connection.prepareStatement(sqlcmdp);
			PreparedStatement prstmtm = connection.prepareStatement(sqlcmdm);

			prstmtc.setLong(1, id);
			ResultSet rsc = prstmtc.executeQuery();			

			if (rsc.next()) {prstmtp.setLong(1, id);// cId
				ResultSet rsp = prstmtp.executeQuery();				
				while (rsp.next()) {
					prstmtm.setLong(1, rsp.getLong("id"));// mId
					ResultSet rsm = prstmtm.executeQuery();
					while (rsm.next()) {
						movie.setId(rsm.getLong("id"));
						movie.setName(rsm.getString("name"));
						movie.setPrice(rsm.getString("price"));
						movies.add(movie);
					}
					mPerson.setId(rsp.getLong("id"));
					mPerson.setIdno(rsp.getString("Idno"));
					mPerson.setMovies(movies);
					mPersons.add(mPerson);

					rsm.close();
					prstmtm.close();

				}
				mCompany.setId(rsc.getLong("id"));
				mCompany.setName(rsc.getString("name"));
				mCompany.setMperson(mPersons);

				rsp.close();
				prstmtp.close();
			}

			rsc.close();
			prstmtc.close();
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
		return mCompany;
	}

	@Override
	public Collection<MCompany> findAll() {
		try {
			connection = ds.getConnection();
			String sqlcmdc = "select * from MCompany";// 先把SQL指令下好
			String sqlcmdp = "select * from MPerson";
			String sqlcmdm = "select * from Movie";

			PreparedStatement prstmtc = connection.prepareStatement(sqlcmdc);// 動態指令使用PreparedStatement
			PreparedStatement prstmtp = connection.prepareStatement(sqlcmdp);
			PreparedStatement prstmtm = connection.prepareStatement(sqlcmdm);

			ResultSet rsc = prstmtc.executeQuery();

			while (rsc.next()) {
				mCompany.setId(rsc.getLong("id"));
				mCompany.setName(rsc.getString("name"));
				ResultSet rsp = prstmtp.executeQuery();
				while (rsp.next()) {// 用while迴圈找出所有的Person
					mPerson.setcId(rsp.getLong("cId"));// Company的id找到Person的cId
					mPerson.setId(rsp.getLong("id"));
					mPerson.setName(rsp.getString("name"));
					ResultSet rsm = prstmtm.executeQuery();
					while (rsm.next()) {// 用while找出所有的movie
						movie.setmId(rsm.getLong("mid"));// Person的id找到Movie的mId
						movie.setId(rsm.getLong("id"));
						movie.setName(rsm.getString("name"));
						movie.setPrice(rsm.getString("price"));
						movies.add(movie);// movie是集合，用movies裝起來回傳
						// log.debug("Find all - movie:" + movies);
					}
					rsm.close();
					mPerson.setMovies(movies);
					mPersons.add(mPerson);
					// log.debug("Find all - mPerson:" + mPersons);

				}
				mCompany.setMperson(mPersons);
				mCompanys.add(mCompany);
				rsp.close();
				// log.debug("Find all - mCompany" + mCompanys);
			}
			rsc.close();
			prstmtm.close();
			prstmtp.close();
			prstmtc.close();
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
		return mCompanys;
	}

	@Override
	public void insert(MCompany entity) {
		try {
			connection = ds.getConnection();
			String sqlcmdc = "insert into MCompany (name) values(?)";
			String sqlcmdp = "insert into MPerson (cId,idno,name) values(?,?,?)";
			String sqlcmdm = "insert into Movie (mId,name,price) values(?,?,?)";

			PreparedStatement prstmtc = connection.prepareStatement(sqlcmdc, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement prstmtp = connection.prepareStatement(sqlcmdp, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement prstmtm = connection.prepareStatement(sqlcmdm, Statement.RETURN_GENERATED_KEYS);

			prstmtc.setString(1, entity.getName());
			prstmtc.executeUpdate();
			ResultSet keySetc = prstmtc.getGeneratedKeys();
			if (keySetc.next()) {
				Long GeneratedIdc = keySetc.getLong("id");
				entity.setId(GeneratedIdc);// 送entity GeneratedID
			}
			for (MPerson mPerson : entity.getMperson()) {
				prstmtp.setLong(1, entity.getId());
				prstmtp.setString(2, mPerson.getIdno());
				prstmtp.setString(3, mPerson.getName());
				prstmtp.executeUpdate();
				ResultSet keySetp = prstmtp.getGeneratedKeys();
				if (keySetp.next()) {
					Long GeneratedIdp = keySetp.getLong("id");
					entity.getMperson().iterator().next().setId(GeneratedIdp);

					for (Movie movie : mPerson.getMovies()) {
						prstmtm.setLong(1, GeneratedIdp);
						prstmtm.setString(2, movie.getName());
						prstmtm.setString(3, movie.getPrice());
						prstmtm.executeUpdate();
					}
				}
				keySetp.close();
			}
			prstmtm.close();
			prstmtp.close();
			prstmtc.close();
			keySetc.close();
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
	public void update(MCompany entity) {// update = 先delete後insert
		MCompanyDao mCompanyDao = new MCompanyDao();
		mCompanyDao.delete(entity.getId());
		mCompanyDao.insert(entity);
	}

	@Override
	public void delete(Long id) {
		Long mId = null;
		try {
			connection = ds.getConnection();
			String sqlcmdc = "delete from MCompany where id = ?";
			String sqlcmdp = "delete from MPerson where cId = ?";
			String sqlcmdm = "delete from Movie where mId = ?";
			String sqlcmd = "select id from MPerson where cId = ?";// 找出全部的電影

			PreparedStatement pstmt = connection.prepareStatement(sqlcmd);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			PreparedStatement pstmtm = connection.prepareStatement(sqlcmdm);// 使用mId先刪掉movie
			while (rs.next()) {
				mId = rs.getLong("id");
				pstmtm.setLong(1, mId);
				pstmtm.executeUpdate();
			}
			PreparedStatement pstmtp = connection.prepareStatement(sqlcmdp);// 使用id刪掉人
			pstmtp.setLong(1, id);
			pstmtp.executeUpdate();

			PreparedStatement pstmtc = connection.prepareStatement(sqlcmdc);
			pstmtc.setLong(1, id);// 最後刪掉公司
			pstmtc.executeUpdate();

			pstmtm.close();
			pstmtp.close();
			pstmtc.close();
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