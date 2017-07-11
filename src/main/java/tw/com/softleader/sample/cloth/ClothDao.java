package tw.com.softleader.sample.cloth;

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

public class ClothDao implements GenericDao<Cloth> {
	
	
	private Logger log = LoggerFactory.getLogger(ClothDao.class);

	
	@Override
	public Cloth findOne(Long id) {
		Cloth entity = null;
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM drink WHERE ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				
				entity = new Cloth();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setColor(rs.getString("color"));
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
	public Collection<Cloth> findAll() {
		
		Collection<Cloth> drinks = new ArrayList<Cloth>();
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM drink";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				
				Cloth drink = new Cloth();
				drink.setId(rs.getLong("id"));
				drink.setName(rs.getString("name"));
				drink.setColor(rs.getString("color"));
				
				drinks.add(drink);
				
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return drinks;
	}

	@Override
	public void insert(Cloth entity) {
		
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
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
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Cloth entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE DRINK SET "
								+ "name = '" + entity.getName() + "', "
								+ "color = '" + entity.getColor() + "'  "
								+ "WHERE ID = " + entity.getId();
			
			stmt.executeUpdate(sqlCmd);
			
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
			
			String sqlCmd = "DELETE FROM DRINK WHERE ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
