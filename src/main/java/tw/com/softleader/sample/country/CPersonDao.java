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
	private CountryDao countryDao = new CountryDao();
	private CPerson cPerson;

	@Override
	public CPerson findOne(Long id) {
		Collection<Country> countries = new ArrayList<Country>();
		cPerson = null;

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM cPerson WHERE id="+id;
			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {
				cPerson = new CPerson();
				cPerson.setId(rs.getLong("id"));
				cPerson.setName(rs.getString("name"));
				cPerson.setIdNo(rs.getString("idno"));

				countries.add(countryDao.findOne(rs.getLong("country")));
				log.info("2:findOne countries-->" + countries);
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
		Collection<Country> countries = new ArrayList<Country>();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM cPerson";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				CPerson cPerson = new CPerson();

				cPerson.setId(rs.getLong("id"));
				cPerson.setName(rs.getString("name"));
				cPerson.setIdNo(rs.getString("idno"));

				countries.add(countryDao.findOne(rs.getLong("country")));
				cPerson.setCountries(countries);

				cPersons.add(cPerson);

			}
			log.info("2:findAll countries--> " + countries);
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
		String sqlCmdPerson = "INSERT INTO cperson(name,idno,country) VALUES(?,?,?)";
		String sqlCmdCountry = "INSERT INTO country(id,name,size)VALUES(?,?,?)";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt1 = connection.prepareStatement(sqlCmdPerson, Statement.RETURN_GENERATED_KEYS);
			while(entity.getCountries().iterator().hasNext()){
			stmt1.setString(1, entity.getName());
			stmt1.setString(2, entity.getIdNo());
			stmt1.setLong(3, entity.getCountries().iterator().next().getId());
			stmt1.executeUpdate();
			
			}
			ResultSet keySet1 = stmt1.getGeneratedKeys();
			log.info("2:keySet1-->" + keySet1);

			if (keySet1.next()) {
				Long generatedId1 = keySet1.getLong(1);
				entity.setId(generatedId1);
			}

			log.info("2:insert sqlCmdPerson-->" + sqlCmdPerson);

			PreparedStatement stmt2 = connection.prepareStatement(sqlCmdCountry, Statement.RETURN_GENERATED_KEYS);
			stmt2.setLong(1, entity.getCountries().iterator().next().getId());
			stmt2.setString(2, entity.getCountries().iterator().next().getName());
			stmt2.setString(3, entity.getCountries().iterator().next().getSize());
			stmt2.executeUpdate();
			ResultSet keySet2 = stmt2.getGeneratedKeys();

			log.info("2:keySet2-->" + keySet2);

			log.info("2:insert sqlCmdCountry-->" + sqlCmdCountry);

			keySet2.close();
			keySet1.close();
			stmt2.close();
			stmt1.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CPerson entity) {
		String sqlCmd = "UPDATE country SET name=?,size=? WHERE id=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			stmt.setString(1, entity.getCountries().iterator().next().getName());
			stmt.setString(2,entity.getCountries().iterator().next().getSize());
			stmt.setLong(3, entity.getCountries().iterator().next().getId());

			stmt.executeUpdate();

			log.info("2:update sqlCmd-->" + sqlCmd);

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "DELETE FROM cperson WHERE ID=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, id);
			stmt.executeUpdate();

			log.info("2:delete sqlCmd-->" + sqlCmd);

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
