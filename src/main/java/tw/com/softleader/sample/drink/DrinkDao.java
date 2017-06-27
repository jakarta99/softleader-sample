package tw.com.softleader.sample.drink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;

public class DrinkDao implements GenericDao<Drink> {

	private final String DB_DRIVER = "org.postgresql.Driver";
	
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	@Override
	public Drink findOne(Long id) {
		
		
		

		
		
		
		return null;
	}

	@Override
	public Collection<Drink> findAll() {
		
		Collection<Drink> drinks = new ArrayList<Drink>();
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM drink";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				
				Drink drink = new Drink();
				drink.setId(rs.getLong("id"));
				drink.setName(rs.getString("name"));
				drink.setColor(rs.getString("color"));
				
				drinks.add(drink);
				
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return drinks;
	}

	@Override
	public void insert(Drink entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Drink entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
