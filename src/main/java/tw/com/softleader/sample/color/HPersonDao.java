package tw.com.softleader.sample.color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class HPersonDao  implements GenericDao<HPerson> {
	
	private Logger log = Logger.getLogger(this.getClass());

	//private final String DB_DRIVER = "org.postgresql.Driver";
	//private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	private ColorDao colorDao = new ColorDao();
	
	private HPerson hperson ; 
	
	@Override
	public HPerson findOne(Long id) {
		
		Collection<Color> colors = new ArrayList<Color>();
		hperson = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM Hperson WHERE ID='" + id + "';";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){

			if (rs.next()) {
				hperson = new HPerson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idNo"));
				
				Iterator<Color> colorInsert = findByIdNo(hperson.getIdNo()).getColors().iterator();
				while (colorInsert.hasNext()){
					colors.add(colorInsert.next());
				}
				
				hperson.setColors(colors);
			
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hperson;
	}
	
	public HPerson findByIdNo(String idno) {

		Collection<Color> colors = new ArrayList<Color>();
		hperson = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM Hperson WHERE IDNO='" + idno + "';";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){

			while (rs.next()) {
				hperson = new HPerson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idNo"));

				colors.add(colorDao.findOne(rs.getLong("color")));

				hperson.setColors(colors);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hperson;
	}

	@Override
	public Collection<HPerson> findAll() {
		Collection<HPerson> hpersons = new ArrayList<HPerson>();
		

		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmd = "SELECT * FROM HPERSON;";
		
		log.debug(sqlCmd);
		
		try (Connection connection = ds.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCmd);){
	
			while(rs.next()) {
				
				Collection<Color> colors = new ArrayList<Color>();
				
				HPerson hperson = new HPerson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idno"));
				
				colors.add(colorDao.findOne(rs.getLong("color")));
				hperson.setColors(colors);

				hpersons.add(hperson);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hpersons;
	}

	@Override
	public void insert(HPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();){

			Long colorsId = null;
			Iterator<Color> colorInsert = entity.getColors().iterator();
			
			while(colorInsert.hasNext()){
				colorsId = colorInsert.next().getId();
				String sqlCmd = "INSERT INTO HPERSON(NAME,IDNO,COLOR) VALUES ('" + entity.getName() + "', '" + entity.getIdNo() + "',"+ colorsId +");";
				log.debug("insert sql : " + sqlCmd);
				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			}

			
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
	public void update(HPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try (Connection connection = ds.getConnection();
			 Statement stmt = connection.createStatement();) {
			
			Iterator<Color> colorInsert = entity.getColors().iterator();
			Long colorsId = colorInsert.next().getId();

			String sqlCmd = "UPDATE HPERSON SET COLOR='" +  colorsId + "' WHERE ID='" + entity.getId() + "';";
			
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
			
			//Iterator<Color> color = findByIdNo(hperson.getIdNo()).getColors().iterator();
			HPerson deleteColor = new HPerson();
			deleteColor = findOne(id);
			log.debug("deletecolor: "+ deleteColor);

			String sqlCmd = "DELETE FROM HPERSON WHERE IDNO='" + deleteColor.getIdNo() + "';";

			stmt.executeUpdate(sqlCmd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
