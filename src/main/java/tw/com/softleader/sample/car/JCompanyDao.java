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

public class JCompanyDao implements GenericDao<JCompany> {
	
	private Logger log = LoggerFactory.getLogger(JCompanyDao.class);

	@Override
	public JCompany findOne(Long id) {
		JCompany entity = null;
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM J_Company WHERE ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				entity = resultSetToJCompany(rs);
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
	public Collection<JCompany> findAll() {
		
		Collection<JCompany> JCompanys = new ArrayList<JCompany>();
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM J_Company";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				JCompany JCompany = resultSetToJCompany(rs);
				JCompanys.add(JCompany);
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JCompanys;
	}

	@Override
	public void insert(JCompany entity) {
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO J_Company (name, english_name, uniform_number) VALUES ('"+entity.getName()+"','"+entity.getEnglishName()+"',"+entity.getUniformNumber()+");";
			
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
	public void update(JCompany entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE J_Company SET "
								+ "name = '" + entity.getName() + "', "
								+ "uniform_number = '" + entity.getUniformNumber() + "', "
								+ "english_name = '" + entity.getEnglishName() + "'  "
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
			
			String sqlCmd = "DELETE FROM J_Company WHERE ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private JCompany resultSetToJCompany(ResultSet rs) throws SQLException{
		JCompany JCompany = new JCompany();
		try {
			JCompany.setId(rs.getLong("id"));
			JCompany.setName(rs.getString("name"));
			JCompany.setEnglishName(rs.getString("english_name"));
			JCompany.setUniformNumber(rs.getString("uniform_number"));
			
		} catch (SQLException e) {
			throw e;
		}
		
		return JCompany;
	}
	
}
