package tw.com.softleader.sample.movie;

import java.sql.Connection;
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

public class MPersonDao implements GenericDao<MPerson> {

	private Logger log = Logger.getLogger(this.getClass());
	private MovieDao movieDao = new MovieDao();
	private MPerson mPerson;

	@Override
	public MPerson findOne(Long id) {
		Collection<Movie> movies = new ArrayList<Movie>();
		mPerson = null;
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select * from mPerson WHERE id=" + id;
			ResultSet rs = statement.executeQuery(sqlCmd);

			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);
			log.fatal("5:" + sqlCmd);

			if (rs.next()) {
				mPerson = new MPerson();
				mPerson.setId(rs.getLong("id"));
				mPerson.setName(rs.getString("name"));
				mPerson.setIdno("idno");

				movies.add(movieDao.findOne(rs.getLong("movie")));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mPerson;
	}

	@Override
	public Collection<MPerson> findAll() {
		Collection<MPerson> mPerson1 = new ArrayList<MPerson>();
		Collection<Movie> movies = new ArrayList<Movie>();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select * from mPerson";
			ResultSet rs = statement.executeQuery(sqlCmd);
			while (rs.next()) {
				MPerson mPerson = new MPerson();
				mPerson.setId(rs.getLong("id"));
				mPerson.setName(rs.getString("name"));
				mPerson.setIdno(rs.getString("idno"));
				movies.add(movieDao.findOne(rs.getLong("movie")));
				mPerson.setMovie(movies);
				mPerson1.add(mPerson);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mPerson1;
	}

	@Override
	public void insert(MPerson entity) {
		String sqlCmdP = "INSERT INTO mperson(name,idno,movie) VALUES(?,?,?)";
		String sqlCmdM = "INSERT INTO movie(id,name,price)VALUES(?,?,?)";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlCmdP, Statement.RETURN_GENERATED_KEYS);
			while (entity.getMovies().iterator().hasNext()) {
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getIdno());
				statement.setLong(3, entity.getMovies().iterator().next().getId());
				statement.executeUpdate();

			}
			ResultSet keySet = statement.getGeneratedKeys();
			if (keySet.next()) {
				Long generatedId = keySet.getLong(1);
				entity.setId(generatedId);
			}
			PreparedStatement statement1 = connection.prepareStatement(sqlCmdM, Statement.RETURN_GENERATED_KEYS);
			statement1.setLong(1, entity.getMovies().iterator().next().getId());
			statement1.setString(2, entity.getMovies().iterator().next().getName());
			statement1.setString(3, entity.getMovies().iterator().next().getPrice());
			statement1.executeUpdate();
			ResultSet keySet1 = statement1.getGeneratedKeys();

			keySet1.close();
			keySet.close();
			statement1.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MPerson entity) {
		String sqlCmd = "UPDATE movie set name=?,price=? WHERE id=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlCmd);
			statement.setString(1, entity.getMovies().iterator().next().getName());
			statement.setString(2, entity.getMovies().iterator().next().getPrice());
			statement.setLong(3, entity.getMovies().iterator().next().getId());
			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "DELETE FROM mperson WHERE ID=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlCmd);
			statement.setLong(1, id);
			statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}