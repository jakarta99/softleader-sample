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

public class MovieDao implements GenericDao<Movie> {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Movie findOne(Long id) {
		Movie entity = null;
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select id,name,price from movie where id=" + id;
			ResultSet rs = statement.executeQuery(sqlCmd);
			
			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);
			log.fatal("5:" + sqlCmd);
			
			if (rs.next()) {
				entity = new Movie();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setPrice(rs.getString("price"));
			} else {
				return null;
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Collection<Movie> findAll() {
		Collection<Movie> movies = new ArrayList<Movie>();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select * from movie";
			ResultSet rs = statement.executeQuery(sqlCmd);
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("price"));
				movies.add(movie);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public void insert(Movie entity) {
		String sqlCmd = "insert into movie(id,name,price)values(?,?,?)";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement preparedstatement = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			preparedstatement.setString(1, entity.getName());
			preparedstatement.setString(2, entity.getPrice());
			preparedstatement.executeUpdate();
			ResultSet keySet = preparedstatement.getGeneratedKeys();
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
				}
			keySet.close();
			preparedstatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Movie entity) {
		String sqlCmd = "update movie set id=?,name=?,price=? where id=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, entity.getId());
			stmt.setString(2, entity.getName());
			stmt.setString(3, entity.getPrice());
			stmt.setLong(4, entity.getId());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "delete from movie where id=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}