package tw.com.softleader.sample.car;

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

public class JPersonDao implements GenericDao<JPerson> {
	
	private Logger log = LoggerFactory.getLogger(JPersonDao.class);
	
	@Override
	public JPerson findOne(Long id) {
		JPerson entity = null;
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM J_Person WHERE ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				entity = resultSetToJPerson(rs);
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
	public Collection<JPerson> findAll() {
		
		Collection<JPerson> jPeople = new ArrayList<JPerson>();
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM J_Person";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				JPerson jPerson = resultSetToJPerson(rs);
				jPeople.add(jPerson);
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jPeople;
	}

	@Override
	public void insert(JPerson entity) {
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO J_Person (name, id_no, j_company_id) VALUES ('"+entity.getName()+"','"+entity.getIdNo()+"',"+entity.getjCompanyId()+");";
			
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
	public void update(JPerson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE J_Person SET "
								+ "name = '" + entity.getName() + "', "
								+ "id_no = '" + entity.getIdNo() + "',  "
								+ "j_company_id = " + entity.getjCompanyId()
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
			
			String sqlCmd = "DELETE FROM J_Person WHERE ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteByJCompanyId(Long jCompanyId){
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "DELETE FROM J_Person WHERE j_company_id = "+jCompanyId;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Collection<JPerson> findByJCompanyId(Long companyId) {
		Collection<JPerson> jPeople = new ArrayList<>();
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM J_Person WHERE j_company_id = " + companyId;
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				JPerson jPerson = resultSetToJPerson(rs);
				jPeople.add(jPerson);
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jPeople;
	}
	
	private JPerson resultSetToJPerson(ResultSet rs) throws SQLException{
		JPerson jPerson = new JPerson();
		jPerson.setId(rs.getLong("id"));
		jPerson.setName(rs.getString("name"));
		jPerson.setIdNo(rs.getString("id_no"));
		jPerson.setjCompanyId(rs.getLong("j_company_id"));
		return jPerson;
	}

}
