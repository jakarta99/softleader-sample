package tw.com.softleader.sample.drink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.GenericDao;

public class DrinkDao implements GenericDao<Drink> {
	
	
	private Logger log = Logger.getLogger(this.getClass());

	private final String DB_DRIVER = "org.postgresql.Driver";
	
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	@Override
	public Drink findOne(Long id) {
		Drink entity = null;
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM drink WHERE ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			log.fatal("5:"+sqlCmd);
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				
				entity = new Drink();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setColor(rs.getString("color"));
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
		
		
		return entity;
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
		
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO DRINK (name, color) VALUES ('"+entity.getName()+"','"+entity.getColor()+"');";
			
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}
			
			keySet.close();
			
			stmt.close();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Drink entity) {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE DRINK SET "
								+ "name = '" + entity.getName() + "', "
								+ "color = '" + entity.getColor() + "'  "
								+ "WHERE ID = " + entity.getId();
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM DRINK WHERE ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
