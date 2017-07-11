package tw.com.softleader.sample.drink;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class DPersonDao implements GenericDao<DPerson> {

	private Logger log = LoggerFactory.getLogger(DPersonDao.class);
	
	
	@Override
	public DPerson findOne(Long id) {
		DPerson entity = null;
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
						
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM d_person WHERE ID = " + id;
			log.info(sqlCmd);
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				
				entity = new DPerson();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setIdno(rs.getString("idno"));
				
				// find drinks by p_id =:id
				String drinksSqlCmd = "SELECT * FROM DRINK WHERE P_ID = "+id;
				log.info(drinksSqlCmd);
				Statement drinksStmt = connection.createStatement();
				ResultSet drinksRs = drinksStmt.executeQuery(drinksSqlCmd);
				
				Collection<Drink> drinks = new ArrayList<Drink>();
				while(drinksRs.next()) {
					Drink drink = new Drink();
					drink.setId(drinksRs.getLong("id"));
					drink.setName(drinksRs.getString("name"));
					drink.setColor(drinksRs.getString("color"));
					
					drinks.add(drink);
				}
				
				entity.setDrinks(drinks);
				
				drinksRs.close();
				drinksStmt.close();
			}
			
			rs.close();
			stmt.close();
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return entity;
	}

	@Override
	public Collection<DPerson> findAll() {
		Collection<DPerson> result = new ArrayList<DPerson>();
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
						
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM d_person ";
			log.info(sqlCmd);
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				DPerson person = new DPerson();
				
				person.setId(rs.getLong("id"));
				person.setName(rs.getString("name"));
				person.setIdno(rs.getString("idno"));
				
				// find drinks by p_id =:id
				String drinksSqlCmd = "SELECT * FROM DRINK WHERE P_ID = "+person.getId();
				log.info(drinksSqlCmd);
				Statement drinksStmt = connection.createStatement();
				ResultSet drinksRs = drinksStmt.executeQuery(drinksSqlCmd);
				
				Collection<Drink> drinks = new ArrayList<Drink>();
				while(drinksRs.next()) {
					Drink drink = new Drink();
					drink.setId(drinksRs.getLong("id"));
					drink.setName(drinksRs.getString("name"));
					drink.setColor(drinksRs.getString("color"));
					
					drinks.add(drink);
				}
				
				person.setDrinks(drinks);
				
				drinksRs.close();
				drinksStmt.close();
				
				result.add(person);
				
			}
			
			rs.close();
			stmt.close();
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public void insert(DPerson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO D_PERSON (name, idno) "
					+ " VALUES ('"+entity.getName()+"', '"+entity.getIdno()+"')";
			log.info(sqlCmd);
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			// get generated id and set DPerson id
			ResultSet keySet = stmt.getGeneratedKeys();
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}
			

			
			for(Drink drink : entity.getDrinks()) {
				sqlCmd = "INSERT INTO DRINK (p_id, name, color) "
					+ "VALUES ("+ entity.getId()+","
							+ "'"+drink.getName()+"',"
							+ "'"+drink.getColor()+"');";
				log.info(sqlCmd);
				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
				keySet = stmt.getGeneratedKeys();
				if(keySet.next()) {
					Long generatedId = keySet.getLong("ID");
					drink.setId(generatedId);
				}
			
			}
			
			keySet.close();
			
			stmt.close();
			
			connection.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(DPerson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE D_PERSON SET "
							+ "name = '" + entity.getName() + "', "
							+ "idno = '" + entity.getIdno() + "'  "
							+ "WHERE ID = " + entity.getId();
			log.info(sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			// delete the drinks and re-insert drinks
			sqlCmd = "DELETE FROM DRINK WHERE P_ID = "+entity.getId();
			log.info(sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			for(Drink drink : entity.getDrinks()) {
				sqlCmd = "INSERT INTO DRINK (p_id, name, color) "
					+ "VALUES ("+ entity.getId()+","
							+ "'"+drink.getName()+"',"
							+ "'"+drink.getColor()+"');";
				log.info(sqlCmd);
				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
				ResultSet keySet = stmt.getGeneratedKeys();
				if(keySet.next()) {
					Long generatedId = keySet.getLong("ID");
					drink.setId(generatedId);
				}
			}
			
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM DRINK WHERE P_ID = "+id;
			log.info(sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd = "DELETE FROM D_PERSON WHERE ID = "+id;
			log.info(sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
