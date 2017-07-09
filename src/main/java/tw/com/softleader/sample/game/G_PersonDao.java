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
import tw.com.softleader.sample.game.G_PersonGame;

public class G_PersonDao implements G_PersonGame<G_Person> {

	private Logger log = LoggerFactory.getLogger(G_PersonDao.class);

	@Override
	public G_Person findOne(Long pId) {
		G_Person entity = null;

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM person WHERE pId = " + pId;

			// log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			// log.warn("3:" + sqlCmd);
			// log.error("4:" + sqlCmd);

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {

				entity = new G_Person();
				entity.setpId(rs.getLong("pId"));
				entity.setpName(rs.getString("pName"));
				entity.setpIdno(rs.getString("pIdno"));
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
	public Collection<G_Person> findAll() {

		Collection<G_Person> persons = new ArrayList<G_Person>();

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM person";
			log.info("2:" + sqlCmd);
			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				G_Person person = new G_Person();
				person.setpId(rs.getLong("pID"));
				person.setpName(rs.getString("pName"));
				person.setpIdno(rs.getString("pIdno"));

				persons.add(person);

			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return persons;
	}

	@Override
	public void insert(G_Person entity) {

		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "INSERT INTO person (pName, pIdno) VALUES ('" + entity.getpName() + "','"
					+ entity.getpIdno() + "');";
			log.info("2:" + sqlCmd);
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet keySet = stmt.getGeneratedKeys();

			if (keySet.next()) {
				Long generatedId = keySet.getLong("pId");
				entity.setpId(generatedId);
			}

			keySet.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(G_Person entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "UPDATE person SET " + "pName = '" + entity.getpName() + "', " + "pIdno = '"
					+ entity.getpId() + "'  " + "WHERE pId = " + entity.getpId();
			log.info("2:" + sqlCmd);
			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			log.info("2:" + sqlCmd);
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
			String sqlCmd = "select id,name,type from game where pId =? order by id";
			log.info("2:" + sqlCmd);
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