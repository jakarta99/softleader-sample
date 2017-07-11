package tw.com.softleader.sample.game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class GameDao implements GenericDao<Game> {

	private Logger log = LoggerFactory.getLogger(GameDao.class);

	@Override
	public Game findOne(Long id) {
		Game entity = null;

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM game WHERE ID = " + id;

			//log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			//log.warn("3:" + sqlCmd);
			//log.error("4:" + sqlCmd);

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {

				entity = new Game();
				entity.setId(rs.getLong("id"));
				entity.setName(rs.getString("name"));
				entity.setType(rs.getString("type"));
			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entity;
	}

	@Override
	public Collection<Game> findAll() {

		Collection<Game> games = new ArrayList<Game>();

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM game";
            
			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				Game game = new Game();
				game.setId(rs.getLong("id"));
				game.setName(rs.getString("name"));
				game.setType(rs.getString("type"));

				games.add(game);

			}
            
			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return games;
	}

	@Override
	public void insert(Game entity) {

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "INSERT INTO game (name, type) VALUES ('" + entity.getName() + "','" + entity.getType()
					+ "');";

			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet keySet = stmt.getGeneratedKeys();

			if (keySet.next()) {
				Long generatedId = keySet.getLong("ID");
				entity.setId(generatedId);
			}

			keySet.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public void update(Game entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "UPDATE DRINK SET " + "name = '" + entity.getName() + "', " + "type = '" + entity.getType()
					+ "'  " + "WHERE ID = " + entity.getId();

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "DELETE FROM game WHERE ID = " + id;

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
