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
	
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM Hperson WHERE ID='" + id + "';";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if (rs.next()) {
				hperson = new HPerson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idNo"));
			}

			String sqlCmd2 = "SELECT * FROM COLOR WHERE PID='" + hperson.getId() + "';";
			rs = stmt.executeQuery(sqlCmd2);
			
			while(rs.next()){
				Color color = new Color();
				color.setName(rs.getString("name"));
				color.setCode(rs.getString("code"));
				color.setPid(rs.getLong("pid"));
				colors.add(color);
			}
			hperson.setColors(colors);
					
			rs.close();

			stmt.close();

			connection.close();

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
		
		try {
			
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM HPERSON;";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				Collection<Color> colors = new ArrayList<Color>();
				HPerson hperson = new HPerson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idno"));
				
				String sqlCmd2 = "SELECT * FROM COLOR WHERE PID='" + hperson.getId() + "';";
				Statement stmt2 = connection.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sqlCmd2);
				while (rs2.next()){
					Color color = new Color();
					color.setName(rs2.getString("name"));
					color.setCode(rs2.getString("code"));
					color.setPid(rs2.getLong("pid"));
					colors.add(color);
				}
				hperson.setColors(colors);
				rs2.close();
				stmt2.close();
				hpersons.add(hperson);
				
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
			
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
			
			String sqlCmd = "INSERT INTO HPERSON(NAME,IDNO) VALUES ('" + entity.getName() + "', '" + entity.getIdNo() + "');";
			log.debug("insert sql(HPerson) : " + sqlCmd);

			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}
			
			Iterator<Color> colorInsert = entity.getColors().iterator();
			
			while(colorInsert.hasNext()){
				Color color = new Color();
				color = colorInsert.next();
				String sqlCmd2 = "INSERT INTO COLOR(NAME,CODE,PID) VALUES ('" + color.getName() + "', '" + color.getCode()+  "', "+ entity.getId() + ");";
				log.debug("insert sql(Color) : " + sqlCmd2);
				stmt.executeUpdate(sqlCmd2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(HPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			String sqlCmdHPERSON = "UPDATE HPERSON SET NAME='" +  entity.getName() + "', IDNO='" + entity.getIdNo()  + "' WHERE ID='" + entity.getId() + "';";
			stmt.executeUpdate(sqlCmdHPERSON);
			
			Statement stmt2 = connection.createStatement();
			String sqlCmdColorDelete = "DELETE FROM COLOR WHERE PID='" + entity.getId() + "';";
			stmt2.executeUpdate(sqlCmdColorDelete);
			
			Iterator<Color> colorUpdate = entity.getColors().iterator();
			while(colorUpdate.hasNext()){
				Color color = new Color();
				color = colorUpdate.next();
				Statement stmt3 = connection.createStatement();
				String sqlCmd3 = "INSERT INTO COLOR(NAME,CODE,PID) VALUES ('" + color.getName() + "', '" + color.getCode()+  "', "+ entity.getId() + ");";
				log.debug("insert sql(Color) : " + sqlCmd3);
				stmt3.executeUpdate(sqlCmd3);
				stmt3.close();
			}

			stmt2.close();

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
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "DELETE FROM COLOR WHERE PID='" + id + "';";
			log.debug("delete sql(Hperson) : " + sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			sqlCmd  = "DELETE FROM HPERSON WHERE ID='" + id + "';";
			log.debug("delete sql(Color) : " + sqlCmd);
			stmt.executeUpdate(sqlCmd);
			
			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
