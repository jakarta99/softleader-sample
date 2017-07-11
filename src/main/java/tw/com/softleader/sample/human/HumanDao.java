package tw.com.softleader.sample.human;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class HumanDao implements GenericDao<Human> {

	@Override
	public Human findOne(Long id) {
		
		Human human = null;
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM human WHERE ID = " + id;
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if (rs.next()) {
				human = new Human();
				human.setId(rs.getLong("id"));
				human.setGender(rs.getString("gender"));
				human.setName(rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return human;
	}

	@Override
	public Collection<Human> findAll() {
		Collection<Human> humans = new ArrayList<Human>();
		
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM human"; 
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while(rs.next()) {
				Human human = new Human();
				human.setId(rs.getLong("id"));
				human.setGender(rs.getString("gender"));
				human.setName(rs.getString("name"));
				humans.add(human);
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return humans;
	}

	@Override
	public void insert(Human entity) {

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO HUMAN (name, gender) VALUES ('"+entity.getName()+"','"+entity.getGender()+"');";
			
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
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Human entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE HUMAN SET "
								+ "name = '" + entity.getName() + "', "
								+ "gender = '" + entity.getGender() + "'  "
								+ "WHERE ID = " + entity.getId();
			
			stmt.executeUpdate(sqlCmd);
			stmt.close();
			connection.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM HUMAN WHERE ID = " + id;
			
			stmt.executeUpdate(sqlCmd);
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
