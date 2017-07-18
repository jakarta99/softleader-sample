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

public class XPersonDao implements GenericDao<XPerson> {

	@Override
	public XPerson findOne(Long id) {
		XPerson entity = null;
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM X_PERSON WHERE ID = " + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if (rs.next()) {
				
				entity = new XPerson();
				entity.setId(rs.getLong("id"));
				entity.setIdNo(rs.getString("idno"));
				entity.setName(rs.getString("name"));
				
				String humansSqlCmd = "SELECT * FROM human WHERE personId = " + id;
				Statement humansStmt = connection.createStatement();
				ResultSet humansRs = humansStmt.executeQuery(humansSqlCmd);
				
				Collection<Human> humans = new ArrayList<>();
				while(humansRs.next()) {
					Human human = new Human();
					human = new Human();
					human.setId(rs.getLong("id"));
					human.setGender(rs.getString("gender"));
					human.setName(rs.getString("name"));
					humans.add(human);
				}
				entity.setHumans(humans);
				
				humansRs.close();
				humansStmt.close();
			}
			
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public Collection<XPerson> findAll() {
		Collection<XPerson> entitys = new ArrayList<>();
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM X_PERSON";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				XPerson xPerson = new XPerson();
				xPerson.setId(rs.getLong("id"));
				xPerson.setIdNo(rs.getString("idno"));
				xPerson.setName(rs.getString("name"));
				
				String humansSqlCmd = "SELECT * FROM human WHERE personId = " + xPerson.getId();
				Statement humansStmt = connection.createStatement();
				ResultSet humansRs = humansStmt.executeQuery(humansSqlCmd);
				
				Collection<Human> humans = new ArrayList<>();
				while(humansRs.next()) {
					Human human = new Human();
					human = new Human();
					human.setId(rs.getLong("id"));
					human.setGender(rs.getString("gender"));
					human.setName(rs.getString("name"));
					humans.add(human);
				}
				xPerson.setHumans(humans);
				
				humansRs.close();
				humansStmt.close();
				
				entitys.add(xPerson);
			}
			
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entitys;
	}

	@Override
	public void insert(XPerson entity) {
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO XPerson (idNo, name) "
					+ " VALUES ('" + entity.getIdNo() + "','"+entity.getName()+"');";
			
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if (keySet.next()) {
				entity.setId(keySet.getLong("id"));
			}
			
			for (Human human : entity.getHumans()) {
				
				String humansSqlCmd = "INSERT INTO HUMAN (name, gender, personId) VALUES ('" + entity.getName() 
				+ "','" + human.getGender() + "','" + human.getPersonId() + "');";
				stmt.execute(humansSqlCmd, Statement.RETURN_GENERATED_KEYS);
				keySet = stmt.getGeneratedKeys();
				
				if (keySet.next()) {
					human.setId(keySet.getLong("ID"));
				}
				
			}
			keySet.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(XPerson entity) {
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE X_PERSON SET "
							+ "name = '" + entity.getName() + "', "
							+ "idno = '" + entity.getIdNo() + "'  "
							+ "WHERE ID = " + entity.getId();
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd = "DELETE FROM human WHERE P_ID = " + entity.getId();
			stmt.executeUpdate(sqlCmd);
			
			for(Human human : entity.getHumans()) {
				sqlCmd = "INSERT INTO Human (personId, name, gender) "
					+ "VALUES ( " + entity.getId() + ","
							+ "'" + human.getName() + "',"
							+ "'" + human.getGender() + "');";
				
				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
				
				ResultSet keySet = stmt.getGeneratedKeys();
				
				if(keySet.next()) {
					human.setId(keySet.getLong("ID"));
				}
			}
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM HUMAN WHERE personId = " + id;
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd = "DELETE FROM D_PERSON WHERE ID = " + id;
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
