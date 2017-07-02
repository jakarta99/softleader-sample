package tw.com.softleader.sample.game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import tw.com.softleader.sample.commons.GenericDao;
//import tw.com.softleader.sample.commons.GenericService;
import tw.com.softleader.sample.game.Game;

public class GameDao implements GenericDao<Game> {

	private final String DB_DRIVER = "org.postgresql.Driver";

	private final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";

	public Game findOne(Long id) {
		Game games = new Game();
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();
			String Stmt1 = "Select * from game where id =" + id;
			ResultSet rs = stmt.executeQuery(Stmt1);

			while (rs.next()) {

				games.setId(rs.getLong("id"));
				games.setName(rs.getString("name"));
				games.setType(rs.getString("type"));
			}
			rs.close();

			stmt.close();

			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return games;
	}

	public Collection<Game> findAll() {

		Collection<Game> games = new ArrayList<Game>();

		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");

			Statement stmt = connection.createStatement();

			String sqlCmd = "Select * from game";

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
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return games;
	}

	@Override
	public void insert(Game entity) {
		// GameDao games = new GameDao();

		try {
			String StmlInsert = "insert into game values (?,?,?)";
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			// Statement stmt = connection.createStatement();
			PreparedStatement pst = connection.prepareStatement(StmlInsert);

			pst.setLong(1, entity.getId());
			pst.setString(2, entity.getName());
			pst.setString(3, entity.getType());
			pst.executeUpdate();

			pst.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Game entity) {
		try {
			String StmlUpdate = "Update game set id =?,name=?,type=?where id =?";

			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement pst = connection.prepareStatement(StmlUpdate);

			pst.setLong(1, entity.getId());
			pst.setString(2, entity.getName());
			pst.setString(3, entity.getType());
			pst.setLong(4, entity.getId());
			pst.executeUpdate();

			pst.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
			String StmlDelete = "Delete from game where id=?";
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, "postgres", "postgres");
			PreparedStatement pst = connection.prepareStatement(StmlDelete);
			pst.setLong(1, id);

			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

// }
