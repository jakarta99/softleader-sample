package tw.com.softleader.sample.movie;

import java.sql.Connection;
import java.sql.DriverManager;
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
			String sqlCmd = "select * from movie where id=" + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("Price"));
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
			String sqlCmd = "SELECT * FROM MOVIE";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getLong("id"));
				movie.setName(rs.getString("name"));
				movie.setPrice(rs.getString("Price"));
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
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "insert into movie values ('" + entity.getId() + "','" + entity.getName() + "','"+ entity.getPrice() + "')";
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
	public void update(Movie entity) {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "update movie set name='" + entity.getName() + "',price='" + entity.getPrice()+ "' where id='" + entity.getId() + "'";
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
			String sqlCmd = "delete from movie where id=" + id;
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