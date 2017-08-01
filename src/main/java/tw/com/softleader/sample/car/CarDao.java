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
import org.springframework.stereotype.Repository;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

@Repository
public class CarDao implements GenericDao<Car> {
	
	private Logger log = LoggerFactory.getLogger(CarDao.class);

	@Override
	public Car findOne(Long id) {
		Car entity = null;
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM car WHERE ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				entity = resultSetToCar(rs);
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
	public Collection<Car> findAll() {
		
		Collection<Car> cars = new ArrayList<Car>();
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM car";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				Car car = resultSetToCar(rs);
				cars.add(car);
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cars;
	}

	@Override
	public void insert(Car entity) {
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO CAR (name, color, brand, j_person_id) VALUES ('"+entity.getName()+"','"+entity.getColor()+"','"+entity.getBrand()+"',"+entity.getjPersonId()+");";
			
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
	public void update(Car entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE CAR SET "
								+ "name = '" + entity.getName() + "', "
								+ "brand = '" + entity.getBrand() + "', "
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
			
			String sqlCmd = "DELETE FROM CAR WHERE ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Collection<Car> findByJPersonId(Long jPersonId) {
		
		Collection<Car> cars = new ArrayList<Car>();
		
		try {
			if (jPersonId == null) {
				return cars;
			}
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM car where j_person_id = "+jPersonId;
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				Car car = resultSetToCar(rs);
				cars.add(car);
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cars;
	}
	
	private Car resultSetToCar(ResultSet rs) throws SQLException{
		Car car = new Car();
		try {
			car.setId(rs.getLong("id"));
			car.setName(rs.getString("name"));
			car.setColor(rs.getString("color"));
			car.setBrand(rs.getString("brand"));
			car.setjPersonId(rs.getLong("j_person_id"));
			
		} catch (SQLException e) {
			throw e;
		}
		
		return car;
	}
	
//	public void deleteByJPersonId(Long jPersonId) {
//		try {
//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
//			Connection connection = ds.getConnection();
//			
//			Statement stmt = connection.createStatement();
//			
//			String sqlCmd = "DELETE FROM CAR WHERE j_person_ID = "+jPersonId;
//			
//			stmt.executeUpdate(sqlCmd);
//			
//			stmt.close();
//			
//			connection.close();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
