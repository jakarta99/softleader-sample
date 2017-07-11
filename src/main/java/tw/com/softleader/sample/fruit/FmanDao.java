package tw.com.softleader.sample.fruit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class FmanDao implements GenericDao<Fman> {

	private final String DB_DRIVER = "org.postgresql.Driver";

	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private FruitDao fruitDao = new FruitDao();
	
	private Fman fman ; 

	@Override
	public Fman findOne(Long id) {
		
		Collection<Fruit> fruits = new ArrayList<Fruit>();
		fman = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM Fman JOIN Fruit ON Fruit = Fruit.ID WHERE Fman.ID=" + id + ";";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){

			while (rs.next()) {
				fman = new Fman();
				fman.setId(rs.getLong("id"));
				fman.setName(rs.getString("name"));
				fman.setIDno(rs.getString("idNo"));

				fruits.add(fruitDao.findOne(rs.getLong("Fruit")));

				fman.setFruits(fruits);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fman;
		}
	
	


	@Override
	public Collection<Fman> findAll() {
		Collection<Fman> fmans = new ArrayList<Fman>();
		Collection<Fruit> fruits = new ArrayList<Fruit>();

		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM FMAN JOIN FRUIT ON FRUIT=FRUIT.ID ORDER BY IDNO;";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){
	
			while(rs.next()) {
				
				Fman fman = new Fman();
				fman.setId(rs.getLong("id"));
				fman.setName(rs.getString("name"));
				fman.setIDno(rs.getString("idno"));
				
				fruits.add(fruitDao.findOne(rs.getLong("color")));
				fman.setFruits(fruits);

				fmans.add(fman);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fmans;
	}


	@Override
	public void insert(Fman entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();){

			Long fruitsId = entity.getFruits().iterator().next().getId();

			String sqlCmd = "INSERT INTO Fman(NAME,IDNO,FRUIT) VALUES ('" + entity.getName() + "', '" + entity.getIDno() + "',"+ fruitsId +");";
			
			log.debug("insert sql : " + sqlCmd);
			
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(Fman entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();) {
			
			Long fruitsId = entity.getFruits().iterator().next().getId();

			String sqlCmd = "UPDATE Fman SET FRUIT=" +  fruitsId + " WHERE ID=" + entity.getId() + ";";
			
			log.debug("update sql: "+ sqlCmd);

			stmt.executeUpdate(sqlCmd);

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();){

			String sqlCmd = "DELETE FROM FMAN WHERE ID='" + id + "';";

			stmt.executeUpdate(sqlCmd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
