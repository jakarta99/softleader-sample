package tw.com.softleader.sample.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;

public class MovieDao implements GenericDao<Movie> {

	private final String DB_DRIVER = "org.postgresql.Driver";
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	@Override
	public Movie findOne(Long id) {
		Movie movie = new Movie();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "select id,name,price from movie where id=" + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			if(rs.next()){
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("price"));
			}else{
				return null;
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}
	@Override
	public Collection<Movie> findAll() {
		Collection<Movie> movies = new ArrayList<Movie>();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "select * from movie";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("price"));
				movies.add(movie);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	@Override
	public void insert(Movie entity) {
		String sqlCmd = "insert into movie(id,name,price)values(?,?,?)";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, entity.getId());
			stmt.setString(2, entity.getName());
			stmt.setString(3, entity.getPrice());
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Movie entity) {
		String sqlCmd = "update movie set id=?,name=?,price=? where id=?";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, entity.getId());
			stmt.setString(2, entity.getName());
			stmt.setString(3, entity.getPrice());
			stmt.setLong(4, entity.getId());
			stmt.executeUpdate();
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
		String sqlCmd = "delete from movie where id=?";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, id);;
			stmt.executeUpdate();
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}