package tw.com.softleader.sample.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class CPersonDao implements GenericDao<CPerson> {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public CPerson findOne(Long personid) {
		CPerson cPerson = new CPerson();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "select p.id,c.countryid,c.name,c.size from cPerson p join country c on p.id=c.personid where p.id=1 AND c.countryid=111";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if (rs.next()) {
				cPerson.setId(rs.getLong("id"));
				cPerson.setCountryid(rs.getLong("countryid"));
				cPerson.setCountryName(rs.getString("name"));
				cPerson.setSize(rs.getString("size"));
			} else {
				return null;
			}
			
			rs.close();
			stmt.close();
			connection.close();
			
			log.info("2:findOne sqlCmd-->" + sqlCmd);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cPerson;
	}

	@Override
	public Collection<CPerson> findAll() {
		Collection<CPerson> cPersons = new ArrayList<CPerson>();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "select p.id,c.countryid,c.name,c.size from cPerson p join country c on p.id=c.personid where p.id=1";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				CPerson cPerson = new CPerson();
				 
				cPerson.setId(rs.getLong("id"));
				cPerson.setName(cPerson.getName());
				cPerson.setIdNo(cPerson.getIdNo());
				
				cPerson.setCountryid(rs.getLong("countryid"));
				cPerson.setCountryName(rs.getString("name"));
				cPerson.setSize(rs.getString("size"));
				
				cPersons.add(cPerson);
			}

			log.info("2:findAll sqlCmd-->" + sqlCmd);

			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cPersons;
	}

	@Override
	public void insert(CPerson entity) {
		String sqlCmd = "insert into country(countryid,name,size,personid)values(?,?,?,1)";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, entity.getCountryid());
			stmt.setString(2, entity.getCountryName());
			stmt.setString(3, entity.getSize());
			stmt.executeUpdate();

			log.info("2:insert sqlCmd-->" + sqlCmd);
			// ResultSet keySet = stmt.getGeneratedKeys();
			// log.info("2:keySet-->"+keySet);
			// if (keySet.next()) {
			// Long generatedId = keySet.getLong("ID");
			// entity.setId(generatedId);
			// }
			//
			// keySet.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CPerson entity) {
		String sqlCmd = "update country set countryid=?,name=?,size=?,personid=1 where countryid=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			stmt.setLong(1, entity.getCountryid());
			stmt.setString(2, entity.getCountryName());
			stmt.setString(3, entity.getSize());
			stmt.setLong(4, entity.getCountryid());
			stmt.executeUpdate();
			
			log.info("2:update sqlCmd-->" + sqlCmd);
			
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long countryid) {
		String sqlCmd = "delete from country where countryid=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, countryid);
			stmt.executeUpdate();
			
			log.info("2:delete sqlCmd-->" + sqlCmd);
			
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
