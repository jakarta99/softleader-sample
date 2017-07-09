package tw.com.softleader.sample.color;

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

public class HpersonDao  implements GenericDao<Hperson> {
	
	private Logger log = Logger.getLogger(this.getClass());

	private final String DB_DRIVER = "org.postgresql.Driver";
	
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	private ColorDao colorDao = new ColorDao();
	
	private Hperson hperson ; 
	
	@Override
	public Hperson findOne(Long id) {
		
		Collection<Color> colors = new ArrayList<Color>();
		hperson = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM Hperson JOIN COLOR ON COLOR = COLOR.ID WHERE HPERSON.ID=" + id + ";";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){

			while (rs.next()) {
				hperson = new Hperson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idNo"));

				colors.add(colorDao.findOne(rs.getLong("color")));

				hperson.setColors(colors);
			}

//			rs.close();
//			stmt.close();
//			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hperson;
	}

	@Override
	public Collection<Hperson> findAll() {
		Collection<Hperson> hpersons = new ArrayList<Hperson>();
		Collection<Color> colors = new ArrayList<Color>();

		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM HPERSON JOIN COLOR ON COLOR=COLOR.ID ORDER BY IDNO;";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){
	
			while(rs.next()) {
				
				Hperson hperson = new Hperson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idno"));
				
				colors.add(colorDao.findOne(rs.getLong("color")));
				hperson.setColors(colors);

				hpersons.add(hperson);
				
			}

//			rs.close();
//			stmt.close();
//			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hpersons;
	}

	@Override
	public void insert(Hperson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();){

			// TODO COLOR VALUE
			String sqlCmd = "INSERT INTO HPERSON(NAME,IDNO,COLOR) VALUES ('" + entity.getName() + "', '" + entity.getIdNo() + "',3);";
			
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
	public void update(Hperson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();
			
			String sqlCmd = "UPDATE Hperson SET COLOR =? WHERE ID=?";

			PreparedStatement stmt = connection.prepareStatement(sqlCmd);

			

			stmt.executeUpdate();

			stmt.close();

			connection.close();

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

			String sqlCmd = "DELETE FROM HPERSON WHERE ID='" + id + "';";

			stmt.executeUpdate(sqlCmd);

//			stmt.close();
//			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
