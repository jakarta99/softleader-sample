package tw.com.softleader.sample.color;

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
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM Hperson WHERE id=" + id + ";";
			
			log.debug(sqlCmd);
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while (rs.next()){
				hperson = new Hperson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idNo"));
				
				colors.add(colorDao.findOne(rs.getLong("color")));
				
				hperson.setColors(colors);
			}

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
	public Collection<Hperson> findAll() {
		Collection<Hperson> hpersons = new ArrayList<Hperson>();
		Collection<Color> colors = new ArrayList<Color>();

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM HPERSON;";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				
				Hperson hperson = new Hperson();
				hperson.setId(rs.getLong("id"));
				hperson.setName(rs.getString("name"));
				hperson.setIdNo(rs.getString("idno"));
				
				colors.add(colorDao.findOne(rs.getLong("color")));
				hperson.setColors(colors);
				
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
	public void insert(Hperson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Hperson entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
