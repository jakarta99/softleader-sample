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

public class ZCompanyDao implements GenericDao<ZCompany> {

	@Override
	public ZCompany findOne(Long id) {
		ZCompany entity = null;
		
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FORM z_company WHERE ID = " + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			if (rs.next()) {
				entity = new ZCompany();
				entity.setId(rs.getLong("id"));
				entity.setType(rs.getString("type"));
			}
			
			sqlCmd = "SELECT * FROM X_PERSON WHERE companyId = " + id;
			Statement personStmt = connection.createStatement();
			ResultSet personRs = personStmt.executeQuery(sqlCmd);
			
			Collection<XPerson> xPersons = new ArrayList<>();
			while (personRs.next()) {
				XPerson xPerson = new XPerson();
				xPerson.setId(personRs.getLong("id"));
				xPerson.setIdNo(personRs.getString("idno"));
				xPerson.setName(personRs.getString("name"));
				
				sqlCmd = "SELECT * FROM HUMAN WHERE personId = " + xPerson.getId();
				Statement humanStmt = connection.createStatement();
				ResultSet humanRs = humanStmt.executeQuery(sqlCmd);
				
				Collection<Human> humans = new ArrayList<>();
				while (humanRs.next()) {
					Human human = new Human();
					human = new Human();
					human.setId(rs.getLong("id"));
					human.setGender(rs.getString("gender"));
					human.setName(rs.getString("name"));
					humans.add(human);
				}
				xPerson.setHumans(humans);
				humanRs.close();
				humanStmt.close();
				xPersons.add(xPerson);
			}
			
			personRs.close();
			personStmt.close();
			
			entity.setMembers(xPersons);

			rs.close();
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public Collection<ZCompany> findAll() {
		Collection<ZCompany> entitys = new ArrayList<>();
		
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FORM z_company";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				ZCompany zCompany = new ZCompany();
				zCompany.setId(rs.getLong("id"));
				zCompany.setType(rs.getString("type"));
				
				sqlCmd = "SELECT * FROM X_PERSON";
				Statement personStmt = connection.createStatement();
				ResultSet personRs = personStmt.executeQuery(sqlCmd);
				
				Collection<XPerson> xPersons = new ArrayList<>();
				while (personRs.next()) {
					XPerson xPerson = new XPerson();
					xPerson.setId(personRs.getLong("id"));
					xPerson.setIdNo(personRs.getString("idno"));
					xPerson.setName(personRs.getString("name"));
					
					sqlCmd = "SELECT * FROM HUMAN ";
					Statement humanStmt = connection.createStatement();
					ResultSet humanRs = humanStmt.executeQuery(sqlCmd);
					
					Collection<Human> humans = new ArrayList<>();
					while (humanRs.next()) {
						Human human = new Human();
						human = new Human();
						human.setId(rs.getLong("id"));
						human.setGender(rs.getString("gender"));
						human.setName(rs.getString("name"));
						humans.add(human);
					}
					xPerson.setHumans(humans);
					humanRs.close();
					humanStmt.close();
					xPersons.add(xPerson);
				}
				
				personRs.close();
				personStmt.close();
				
				zCompany.setMembers(xPersons);
				entitys.add(zCompany);
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
	public void insert(ZCompany entity) {
		
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO Z_COMPANY (id, type) "
					+ "VALUES( + '" + entity.getId() + "' + '" + entity.getType() +"' );";
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getLong("id"));
			}
			
			for (XPerson p : entity.getMembers()) {
				sqlCmd = "INSERT INTO XPerson (idNo, name, companyId) "
						+ " VALUES ('" + p.getIdNo() + "','"+p.getName()+"' +'" + entity.getId() + "'+);";
				
				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
				generatedKeys = stmt.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					entity.setId(generatedKeys.getLong("id"));
				}
				
				for (Human human : p.getHumans()) {
					
					String humansSqlCmd = "INSERT INTO HUMAN (name, gender, personId) VALUES ('" + human.getName() 
					+ "','" + human.getGender() + "','" + p.getId() + "');";
					stmt.execute(humansSqlCmd, Statement.RETURN_GENERATED_KEYS);
					generatedKeys = stmt.getGeneratedKeys();
					
					if (generatedKeys.next()) {
						human.setId(generatedKeys.getLong("ID"));
					}
					
				}
				generatedKeys.close();
			}
			
			stmt.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(ZCompany entity) {
		// TODO Auto-generated method stub
		try {
			DataSource dataSource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE Z_COMPANY SET "
					+ "TYPE = '" + entity.getType() + "'"
					+ "WHERE ID = '" + entity.getId() + "';";
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd = "SELECT FROM X_PERSON WHERE COMPANYID = " + entity.getId();
			ResultSet personRs = stmt.executeQuery(sqlCmd);
			
			while(personRs.next()) {
				sqlCmd = "DELETE FROM HUMAN WHERE PERSONID = " + personRs.getLong("id");
				stmt.executeUpdate(sqlCmd);
				
			}
			
			sqlCmd = "DELETE FROM X_PERSON WHERE COMPANYID = " + entity.getId();
			stmt.executeUpdate(sqlCmd);
			
			for (XPerson p : entity.getMembers()) {
				
				String xpersonCmd = "INSERT INTO XPerson (idNo, name, companyId) "
						+ " VALUES ('" + p.getIdNo() + "','"+p.getName()+"','" + entity.getId() +"');";
				
				stmt.execute(xpersonCmd, Statement.RETURN_GENERATED_KEYS);
				ResultSet keySet = stmt.getGeneratedKeys();
				
				if (keySet.next()) {
					entity.setId(keySet.getLong("id"));
				}
				
				for (Human h : p.getHumans()) {
					
					String humansSqlCmd = "INSERT INTO HUMAN (name, gender, personId) VALUES ('" + h.getName() 
					+ "','" + h.getGender() + "','" + p.getId() + "');";
					
					stmt.execute(humansSqlCmd, Statement.RETURN_GENERATED_KEYS);
					keySet = stmt.getGeneratedKeys();
					
					if (keySet.next()) {
						h.setId(keySet.getLong("ID"));
					}
				}
				
				keySet.close();
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
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM Z_COMPANY WHERE ID = " + id;
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd = "SELECT FROM X_PERSON WHERE COMPANYID = " + id;
			ResultSet personRs = stmt.executeQuery(sqlCmd);
			
			while (personRs.next()) {
				
				sqlCmd = "DELETE FROM HUMAN WHERE PERSONID = " + personRs.getLong("id");
				stmt.executeUpdate(sqlCmd);
				
				sqlCmd = "DELETE FROM X_PERSON WHERE ID = " + personRs.getLong("id");
				stmt.executeUpdate(sqlCmd);
			}
			
			personRs.close();
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
