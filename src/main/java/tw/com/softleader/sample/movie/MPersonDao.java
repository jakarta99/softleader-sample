package tw.com.softleader.sample.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class MPersonDao implements GenericDao<MPerson> {
	
	Collection<Movie>movies = new ArrayList<>();
	Collection<MPerson>mpersons = new ArrayList<>();
	
	@Override
	public MPerson findOne(Long id) {
		MPerson entity = null;
		String sqlCmd = "select * from mperson WHERE ID = "+id;
		String sqlcmd1 = "select * from movie where pid =?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			if(rs.next()) {
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setIdno(rs.getString("Idno"));
				//找出pid.setMovie(movies);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Collection<MPerson> findAll() {
		MPerson entity1 = null;
		String sqlcmd1 = "SELECT * FROM mpreson";
		String sqlcmd2 = "SELECT * FROM movie";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlcmd1);
			ResultSet rs1 = stmt.executeQuery(sqlcmd2);
			while(rs.next()) {
				entity1.setId(rs.getLong("id"));
				entity1.setName(rs.getString("name"));
				entity1.setIdno(rs.getString("idno"));
				//entity1找出pid movie
				mpersons.add(entity1);
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(MPerson entity) {
		String sqlcmd = "insert into mperson(id,name,idno) value = (?,?,?)";
		String sqlcmd1 = "insert into movie(id,pid,name,price) value = (?,?,?,?)";
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();			
			PreparedStatement stmt1 = connection.prepareStatement(sqlcmd1);
			PreparedStatement stmt = connection.prepareStatement(sqlcmd);
			
			stmt.execute(sqlcmd, Statement.RETURN_GENERATED_KEYS);
			//stmt.setLong(per, x);
			
			stmt.close();
			connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MPerson entity) {
		String sqlcmd1 = "Delete movie where pid = ?";
		String sqlcmd2 = "Delete mperson where id = ?";
		String sqlcmd3 = "INSERT INTO mperson(id,name,idno)value = (?,?,?)";
		String sqlcmd4 = "INSERT INTO movie(id,name,pid,name)value = (?,?,?)";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sqlcmd1);
			
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		String sqlCmd = "delete from movie where pid=?";
		String sqlCmd1 = "delete from mperson where id=?";
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sqlCmd);
			PreparedStatement stmt1 = connection.prepareStatement(sqlCmd1);
			stmt.setLong(1, id);
			stmt1.setLong(1, id);
			
			stmt.executeUpdate();
			stmt1.executeUpdate();
			stmt1.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}