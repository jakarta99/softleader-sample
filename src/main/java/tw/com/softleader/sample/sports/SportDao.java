package tw.com.softleader.sample.sports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class SportDao implements GenericDao<Sport> {

	@Override
	public Sport findOne(Long id) {

		Sport sport = new Sport();

		try {
			DataSource datasource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = datasource.getConnection();

			String sqlCmd = "SELECT * FROM sport WHERE id=?";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				sport.setId(rs.getLong("id"));
				sport.setName(rs.getString("name"));
				sport.setPeople(rs.getString("people"));
				return sport;
			}
			rs.close();
			pstmt.close();
			connection.close();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<Sport> findAll() {

		Collection<Sport> sports = new ArrayList<Sport>();

		try {
			DataSource datasource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = datasource.getConnection();
			
			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM sport";

			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {
				Sport sport = new Sport();
				sport.setId(rs.getLong("id"));
				sport.setName(rs.getString("name"));
				sport.setPeople(rs.getString("people"));

				sports.add(sport);
			}
			rs.close();
			stmt.close();
			connection.close();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sports;
	}

	@Override
	public void insert(Sport entity) {

		try {
			DataSource datasource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = datasource.getConnection();
			
			String sqlCmd = "INSERT INTO sport(name,people) VALUES (?,?)";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getPeople());
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Sport entity) {
		try {
			DataSource datasource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = datasource.getConnection();
		
			String sqlCmd = "UPDATE sport SET id = ? , name = ? ,people = ? WHERE id = ? ";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setLong(1, entity.getId());
			pstmt.setString(2, entity.getName());
			pstmt.setString(3, entity.getPeople());
			pstmt.setLong(4, entity.getId());

			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			DataSource datasource = DataSourceUtil.getInstance().getDataSource();
			Connection connection = datasource.getConnection();
		
			String sqlCmd = "DELETE FROM sport WHERE id = ?";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
