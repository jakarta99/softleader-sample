package tw.com.softleader.sample.game;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.commons.DataSourceUtil;
//import tw.com.softleader.sample.commons.GenericDao;
import tw.com.softleader.sample.game.GPersonGame;

public class GPersonDao implements GPersonGame<GPerson> {

	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(GPersonDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public GPerson findOne(Long pId) {
		GPerson entity = null;

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM person WHERE pId = " + pId;
			// String sqlCmd2 = "SELECT * FROM game Where id="+entity.getId();

			// log.info("2:" + sqlCmd);

			ResultSet rs = stmt.executeQuery(sqlCmd);
			// ResultSet rs2 = stmt.executeQuery(sqlCmd2);
			
			
			
			if (rs.next()) {
				entity = new GPerson();
				entity.setpId(rs.getLong("pId"));
				entity.setpName(rs.getString("pName"));
				entity.setpIdno(rs.getString("pIdno"));
				entity.setGames((Collection<Game>) rs.getObject("id"));
				entity.setGames((Collection<Game>) rs.getObject("name"));
				entity.setGames((Collection<Game>) rs.getObject("type"));
			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<GPerson> findAll() {

		Collection<GPerson> persons = new ArrayList<GPerson>();

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM person";
			//log.info("2:" + sqlCmd);
			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				GPerson person = new GPerson();
				person.setpId(rs.getLong("pID"));
				person.setpName(rs.getString("pName"));
				person.setpIdno(rs.getString("pIdno"));
				person.setGames((Collection<Game>) rs.getObject("id"));
				person.setGames((Collection<Game>) rs.getObject("name"));
				person.setGames((Collection<Game>) rs.getObject("type"));
				persons.add(person);

			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return persons;
	}

	@Override
	public void insert(GPerson entity) {

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			String sqlCmd = "INSERT INTO person (pName, pIdno) VALUES (?,?);";
			// log.info("2:" + sqlCmd);
			String sqlCmd2 = "INSERT INTO game(name,type)VALUES(?,?);";
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			stmt2.execute(sqlCmd2, Statement.RETURN_GENERATED_KEYS);
			ResultSet keySet1 = stmt.getGeneratedKeys();
			ResultSet keySet2 = stmt2.getGeneratedKeys();
			if (keySet1.next()) {
				Long generatedId = keySet1.getLong("pId");
				entity.setpId(generatedId);

			}

			keySet1.close();

			stmt.close();

			//connection.close();

			if (keySet2.next()) {

				@SuppressWarnings("unchecked")
				Collection<Game> generatedName = (Collection<Game>) keySet2.getObject("name");
				entity.setGames(generatedName);
				@SuppressWarnings("unchecked")
				Collection<Game> generatedType = (Collection<Game>) keySet2.getObject("type");
				entity.setGames(generatedType);

			}
			keySet2.close();
			stmt2.close();
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(GPerson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "UPDATE person SET " + "pName = '" + entity.getpName() + "', " + "pIdno = '"
					+ entity.getpId() + "'  " + "WHERE pId = " + entity.getpId();

			// log.info("2:" + sqlCmd);

			String sqlCmd2 = "UPDATE game SET (?,?)";
			
		
			
			stmt.executeUpdate(sqlCmd);
			stmt.executeUpdate(sqlCmd2);
			stmt.close();

			connection.close();
          
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long pId) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();

			String sqlCmd = "DELETE FROM person WHERE pId = " + pId;
			
			//log.info("2:" + sqlCmd);
			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Game> getGameByPerson(Long pId) {
		List<Game> list = new ArrayList<Game>();

		try {
			String sqlCmd = "select id,name,type from game where pId =? ";
			//log.info("2:" + sqlCmd);
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {
				Game game = new Game();
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setType(rs.getString("type"));
				list.add(game);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}