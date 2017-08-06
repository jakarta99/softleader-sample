package tw.com.softleader.sample.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

@Repository
public class MovieDao implements GenericDao<Movie> {

	Connection connection = null;
	DataSource ds = DataSourceUtil.getInstance().getDataSource();

	@Override
	public Movie findOne(Long id) {
		Movie movie = new Movie();
		try {
			connection = ds.getConnection();
			String sqlcmd = "select * from Movie where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sqlcmd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt.setLong(1, id);
			} else {
				return null;
			}
			rs.close();
			pstmt.close();
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
		return movie;
	}

	@Override
	public Collection<Movie> findAll() {
		Collection<Movie> movies = new ArrayList<Movie>();
		try {
			connection = ds.getConnection();
			String sqlcmd = "select * from Movie ";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlcmd);
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setmId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("price"));
				movies.add(movie);
			}
			rs.close();
			stmt.close();
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
		return movies;
	}

	@Override
	public void insert(Movie entity) {
		try {
			connection = ds.getConnection();
			String sqlcmd = "insert into Movie(name,price) value (?,?) ";
			PreparedStatement pstmt = connection.prepareStatement(sqlcmd);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPrice());
			pstmt.executeUpdate();
			ResultSet keySet = pstmt.getGeneratedKeys();
			if (keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}
			keySet.close();
			pstmt.close();
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
	public void update(Movie entity) {
		try {
			connection = ds.getConnection();
			String sqlcmd = "update Movie set name=? , price=? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sqlcmd);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPrice());
			pstmt.setLong(3, entity.getId());
			pstmt.executeUpdate();
			pstmt.close();
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
		try {
			connection = ds.getConnection();
			String sqlcmd = "delete from Movie where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sqlcmd);
			pstmt.executeQuery();
			pstmt.setLong(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
