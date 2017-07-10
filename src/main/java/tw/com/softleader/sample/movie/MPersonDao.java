package tw.com.softleader.sample.movie;

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

public class MPersonDao implements GenericDao<MPerson> {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public MPerson findOne(Long id) {
		MPerson entity = null;
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select id,name,idno,moviename1,movie2,movie3 from mperson where id=" + id;
			ResultSet rs = statement.executeQuery(sqlCmd);
			
			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);
			log.fatal("5:" + sqlCmd);
			
			if (rs.next()) {
				entity = new MPerson();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setIdno("idno");
				entity.setMoviename1("moviename1");
				entity.setMoviename2("moviename2");
				entity.setMoviename3("moviename3");				
			} else {
				return null;
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Collection<MPerson> findAll() {
		Collection<MPerson> mpersons = new ArrayList<MPerson>();
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sqlCmd = "select * from mperson";
			ResultSet rs = statement.executeQuery(sqlCmd);
			while (rs.next()) {
				MPerson mperson = new MPerson();
				mperson.setId(rs.getLong("id"));
				mperson.setName(rs.getString("name"));
				mperson.setIdno(rs.getString("idno"));
				mperson.setMoviename1(rs.getString("moviename1"));
				mperson.setMoviename2(rs.getString("moviename2"));
				mperson.setMoviename3(rs.getString("moviename3"));
				mpersons.add(mperson);
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mpersons;
	}

	@Override
	public void insert(MPerson entity) {
		
	}

	@Override
	public void update(MPerson entity) {
		
	}

	@Override
	public void delete(Long id) {
		
		
	}
}