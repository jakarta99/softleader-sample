package tw.com.softleader.sample.country;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;

public class CountryDao implements GenericDao<Country> {

	private final String DB_DRIVER = "org.postgresql.Driver";
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

	@Override
	public Country findOne(Long id) {
		Country country = new Country();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "select id,name,size from country where id=" + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				country.setId(rs.getLong("id"));
				country.setName(rs.getString("name"));
				country.setSize(rs.getString("size"));
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	@Override
	public Collection<Country> findAll() {
		Collection<Country> countries = new ArrayList<Country>();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();
			String sqlCmd = "select * from country";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				Country country = new Country();
				country.setId(rs.getLong("id"));
				country.setName(rs.getString("name"));
				country.setSize(rs.getString("size"));
				countries.add(country);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	@Override
	public void insert(Country entity) {
		String sqlCmd = "insert into country(id,name,size)values(?,?,?)";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			stmt.setLong(1, entity.getId());
			stmt.setString(2, entity.getName());
			stmt.setString(3, entity.getSize());
			stmt.executeUpdate();
			// System.out.println("Insert Completed");

			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Country entity) {
		String sqlCmd = "update country set id=?,name=?,size=? where id=?";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			stmt.setLong(1, entity.getId());
			stmt.setString(2, entity.getName());
			stmt.setString(3, entity.getSize());
			stmt.setLong(4, entity.getId());
			stmt.executeUpdate();
			// System.out.println("Update Completed");

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
		String sqlCmd = "delete from country where id=?";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			// System.out.println("Delete Completed");

			stmt.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
