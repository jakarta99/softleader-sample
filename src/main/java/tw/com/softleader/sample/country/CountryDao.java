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

public class CountryDao implements GenericDao<Country> {

	private DataSource ds;
	public void setDataSource(DataSource dataSource){
		this.ds=dataSource;
	}
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public Country findOne(Long id) {
		Country country = new Country();
		try {
			//DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "select id,name,size from country where id=" + id;
			ResultSet rs = stmt.executeQuery(sqlCmd);
			if (rs.next()) {
				country.setId(rs.getLong("id"));
				country.setName(rs.getString("name"));
				country.setSize(rs.getString("size"));
			} else {
				return null;
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return country;
	}

	@Override
	public Collection<Country> findAll() {
		Collection<Country> countries = new ArrayList<Country>();
		try {
			//DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "select * from country";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while (rs.next()) {
				Country country = new Country();
				country.setId(rs.getLong("id"));
				country.setName(rs.getString("name"));
				country.setSize(rs.getString("size"));
				countries.add(country);
			}
			
			log.info("2:sqlCmd-->"+sqlCmd);
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return countries;
	}

	@Override
	public void insert(Country entity) {
		String sqlCmd = "insert into country(name,size)values(?,?)";
		try {
			//DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getSize());
			stmt.executeUpdate();

			ResultSet keySet = stmt.getGeneratedKeys();
			log.info("2:keySet-->"+keySet);
			if (keySet.next()) {
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
	public void update(Country entity) {
		String sqlCmd = "update country set name=?,size=? where id=?";
		try {
			//DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getSize());
			stmt.setLong(3, entity.getId());
			stmt.executeUpdate();

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "delete from country where id=?";
		try {
			//DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			stmt.setLong(1, id);
			stmt.executeUpdate();

			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
