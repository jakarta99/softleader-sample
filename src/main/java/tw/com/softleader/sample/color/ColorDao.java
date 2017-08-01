package tw.com.softleader.sample.color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

@Repository
public class ColorDao implements GenericDao<Color>{
	
//	private final String DB_DRIVER = "org.postgresql.Driver";
//	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	
	private Color color ; 

	@Override
	public Color findOne(Long id) {
		
		try {
			//Class.forName(DB_DRIVER);
			//Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM COLOR WHERE id=" + id + ";";

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {
				color = new Color();
				color.setId(rs.getLong("id"));
				color.setName(rs.getString("name"));
				color.setCode(rs.getString("code"));
			} else {
				return null;
			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return color;
	}

	@Override
	public Collection<Color> findAll() {
		Collection<Color> colors = new ArrayList<Color>();
		
		try {
			//Class.forName(DB_DRIVER);
			//Connection connection = DriverManager.getConnection(DB_URL,"postgres", "postgres");
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM COLOR;";
			
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return colors;
	}

	@Override
	public void insert(Color entity) {
		try {
			//Class.forName(DB_DRIVER);
			//Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "INSERT INTO COLOR(NAME,CODE) VALUES ('" + entity.getName() + "', '" + entity.getCode() + "');";

			//stmt.executeUpdate(sqlCmd);
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if(keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Color entity) {
		try {
			//Class.forName(DB_DRIVER);
			//Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "UPDATE COLOR SET NAME='" + entity.getName() + "', CODE='" + entity.getCode() + "' WHERE ID='" + entity.getId() + "';";

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Long id) {
		try {
			//Class.forName(DB_DRIVER);
			//Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "DELETE FROM COLOR WHERE ID='" + id + "';";

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
