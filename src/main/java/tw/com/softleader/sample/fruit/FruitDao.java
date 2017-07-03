package tw.com.softleader.sample.fruit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;

public class FruitDao implements GenericDao<Fruit> {

	private final String DB_DRIVER = "org.postgresql.Driver";

	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

	@Override
	public Fruit findOne(Long id) {

		Fruit fruit = new Fruit();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();

			String sqlCmd = ("SELECT * from Fruit WHERE id = " + id);
			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {

				fruit.setId(rs.getLong("id"));
				fruit.setName(rs.getString("name"));
				fruit.setColor(rs.getString("color"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fruit;

	}

	@Override
	public Collection<Fruit> findAll() {

		Collection<Fruit> fruits = new ArrayList<Fruit>();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * from Fruit";

			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				Fruit fruit = new Fruit();
				fruit.setId(rs.getLong("id"));
				fruit.setName(rs.getString("name"));
				fruit.setColor(rs.getString("color"));

				fruits.add(fruit);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fruits;
	}

	@Override
	public void insert(Fruit entity) {

		String sqlCmd = "insert into fruit(id,name,color)values(?,?,?)";
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres","postgres");
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			
			pstmt.setLong(1, entity.getId() );
			pstmt.setString(2, entity.getName());
			pstmt.setString(3, entity.getColor());
			pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void update(Fruit entity) {
		String sqlCmd = "update fruit set id=?,name=?,color=? where id=?";
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres","postgres");
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			
			pstmt.setLong(1, entity.getId());
			pstmt.setString(2, entity.getName());
			pstmt.setString(3, entity.getColor());
			pstmt.setLong(4, entity.getId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "delete from fruit where id=?";
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres","postgres");
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
