package tw.com.softleader.sample.color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;


public class ColorDao implements GenericDao<Color>{
	
	private final String DB_DRIVER = "org.postgresql.Driver";
	
	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

	@Override
	public Color findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Color> findAll() {
		Collection<Color> colors = new ArrayList<Color>();
		
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM Color";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				
				Color color = new Color();
				color.setId(rs.getLong("id"));
				color.setName(rs.getString("name"));
				color.setCode(rs.getString("code"));
				
				colors.add(color);
				
			}
			
			rs.close();
			
			stmt.close();
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return colors;
	}

	@Override
	public void insert(Color entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Color entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
