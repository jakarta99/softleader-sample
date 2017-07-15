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
	Long GeneratedIdc = null;
	Collection<MPerson> mPersons = new ArrayList<MPerson>();
	MPerson mPerson = new MPerson();
	Long GeneratedIdp = null;
	Collection<Movie> movies = new ArrayList<Movie>();
	Movie movie = new Movie();

	@Override
	public MCompany findOne(Long id) {

		String sqlcmdc = "select * from MCompany where id = ?";// 先把SQL指令下好
		String sqlcmdp = "select * from MPerson where cId = ?";
		String sqlcmdm = "select * from Movie where mId = ?";

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();// 連線
			Connection connection = ds.getConnection();

			PreparedStatement prstmtc = connection.prepareStatement(sqlcmdc);// 動態指令使用PreparedStatement
			PreparedStatement prstmtp = connection.prepareStatement(sqlcmdp);
			PreparedStatement prstmtm = connection.prepareStatement(sqlcmdm);

			prstmtc.setLong(1, id);
			ResultSet rsc = prstmtc.executeQuery();
			prstmtp.setLong(1, id);// cId

			if (rsc.next()) {
				mCompany.setId(rsc.getLong("id"));
				mCompany.setName(rsc.getString("name"));
				ResultSet rsp = prstmtp.executeQuery();
				prstmtm.setLong(1, rsp.getLong("id"));// mId
				while (rsp.next()) {
					mPerson.setId(rsp.getLong("id"));
					mPerson.setIdno(rsp.getString("Idno"));
					ResultSet rsm = prstmtm.executeQuery();
					while (rsm.next()) {
						movie.setId(rsm.getLong("id"));
						movie.setName(rsm.getString("name"));
						movie.setPrice(rsm.getString("price"));
						movies.add(movie);
						log.debug("Find one - movies:" + movies);
						rsm.close();
					}
					mPerson.setMovies(movies);
					mPersons.add(mPerson);
					log.debug("Find one - mPersons:" + mPersons);
					rsp.close();
				}
				mCompany.setMperson(mPersons);
				log.debug("Find one - mCompany:" + mCompany);
			}
			rsc.close();
			prstmtm.close();
			prstmtp.close();
			prstmtc.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mCompany;
	}

	@Override
	public Collection<MCompany> findAll() {
		String sqlcmdc = "select * from MCompany";// 先把SQL指令下好
		String sqlcmdp = "select * from MPerson";
		String sqlcmdm = "select * from Movie";

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();// 連線
			Connection connection = ds.getConnection();

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
						log.debug("Find all - movie:" + movies);
						rsm.close();
					}
					mPerson.setMovies(movies);
					mPersons.add(mPerson);
					log.debug("Find all - mPerson:" + mPersons);
					rsp.close();
				}
				mCompany.setMperson(mPersons);
				mCompanys.add(mCompany);
				log.debug("Find all - mCompany" + mCompanys);
			}
			rsc.close();
			prstmtm.close();
			prstmtp.close();
			prstmtc.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mCompanys;
	}

	@Override
	public void insert(MCompany entity) {
		String sqlcmdc = "insert into MCompany (name) values=(?)";
		String sqlcmdp = "insert into MPerson (cId,idno,name) values=(?,?,?)";
		String sqlcmdm = "insert into Movie (mId,name,price) values=(?,?,?)";

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement prstmtc = connection.prepareStatement(sqlcmdc, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement prstmtp = connection.prepareStatement(sqlcmdp, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement prstmtm = connection.prepareStatement(sqlcmdm);

			prstmtc.setString(1, entity.getName());
			prstmtc.executeUpdate();
			ResultSet keySetc = prstmtc.getGeneratedKeys();

			if (keySetc.next()) {// 將Generated塞進entity
				Long GeneratedIdc = keySetc.getLong("id");
				entity.setId(GeneratedIdc);
			}
			ResultSet keySetp = prstmtp.getGeneratedKeys();
			while (keySetp.next()) {
				Long GeneratedIdp = keySetp.getLong("id");

				prstmtm.setLong(1, GeneratedIdp);
				prstmtm.setString(2, entity.getMperson().iterator().next().getMovies().iterator().next().getName());
				prstmtm.setString(3, entity.getMperson().iterator().next().getMovies().iterator().next().getPrice());
				prstmtm.executeUpdate();

				for (Movie movie : movies) {
					movie.setmId(GeneratedIdp);
					movie.setName(entity.getMperson().iterator().next().getMovies().iterator().next().getName());
					movie.setPrice(entity.getMperson().iterator().next().getMovies().iterator().next().getPrice());
					log.debug("Insert - Movie(data):" + movie);
				}
			}
			keySetc.close();
			prstmtm.close();
			prstmtp.close();
			prstmtc.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MCompany entity) {// update = 先delete後insert
		MCompanyDao.this.delete(entity.getId());
		MCompanyDao.this.insert(entity);
	}

	@Override
	public void delete(Long id) {
		String sqlcmdc = "delete MCompany where id = ?";
		String sqlcmdp = "delete MPerson where cId = ?";
		String sqlcmdm = "delete Movie where mId = ?";

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement pstmtc = connection.prepareStatement(sqlcmdc);// 先找出有沒有這個公司id
			ResultSet keySetc = pstmtc.getGeneratedKeys();

			if (keySetc.next()) {// 有的話找出id設成cId
				Long cId = keySetc.getLong(1);
				log.debug(sqlcmdc);

				PreparedStatement pstmtp = connection.prepareStatement(sqlcmdp);// 換找有沒有cId的人
				ResultSet keySetp = pstmtp.getGeneratedKeys();// 找人的id
				if (keySetp.next()) {// 有的話找出id設成mId
					Long mId = keySetp.getLong(1);
					log.debug(sqlcmdp);

					PreparedStatement pstmtm = connection.prepareStatement(sqlcmdm);// 使用mId先刪掉movie
					pstmtm.setLong(1, mId);
					pstmtm.executeQuery();
					pstmtm.close();
				}
				pstmtp.setLong(1, cId);// 使用cId刪掉人
				pstmtp.close();
			}
			pstmtc.setLong(1, id);// 最後刪掉公司
			pstmtc.executeUpdate();

			keySetc.close();
			pstmtc.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}