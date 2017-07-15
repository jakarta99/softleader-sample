package tw.com.softleader.sample.game;

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
import tw.com.softleader.sample.commons.GenericService;

public class GcompanyDao implements GenericDao<Gcompany> {

	@Override
	public Gcompany findOne(Long id) {
		try {
			Collection<GPerson> persons = new ArrayList<GPerson>();
			Collection<Game> games = new ArrayList<Game>();
			Collection<Gcompany> company = new ArrayList<Gcompany>();
			GPerson persons1 = null;
			Gcompany company1 = null;
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection();
			String sqlCmd = "select * from company where id =?";
			String sqlCmd2 = "select * from person where pId =?";
			String sqlCmd3 = "select * from ame where gId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
			ResultSet rs = pstmt.executeQuery();
			pstmt.setLong(1, id);

			if (rs.next()) {
				Gcompany entity = new Gcompany();
				entity.setId(rs.getLong("id"));
				entity.setGCname(rs.getString("gCname"));

			}
			rs.close();
			pstmt.close();

			PreparedStatement pstmt2 = conn.prepareStatement(sqlCmd2);
			ResultSet rs2 = pstmt2.executeQuery();
			pstmt2.setLong(1, id);
			while (rs2.next()) {
				GPerson person = new GPerson();

				person.setpId(rs2.getLong("pId"));
				person.setpIdno(rs2.getString("idno"));
				person.setpName(rs2.getString("pName"));
				persons.add(person);
                   company1.setGpersons(persons);
			}
		

			rs2.close();
			pstmt2.close();

			PreparedStatement pstmt3 = conn.prepareStatement(sqlCmd3);
			ResultSet rs3 = pstmt3.executeQuery();
			pstmt.setLong(1, id);
			while (rs3.next()) {
				Game game = new Game();
				game.setgId(rs3.getLong("gId"));
				game.setName(rs3.getString("name"));
				game.setType(rs3.getString("type"));
				games.add(game);

			}
			 //persons1.setGames(games);
			//company1.setGames(games);

			pstmt3.close();
			rs3.close();
			conn.close();

		} catch (

		SQLException e) {

			e.printStackTrace();
		}
		///return company1;
		return null;

	}

	@Override
	public Collection<Gcompany> findAll() {

		try {
			Collection<GPerson> persons = new ArrayList<GPerson>();
			Collection<Gcompany> company = new ArrayList<Gcompany>();
			Collection<Game> games = new ArrayList<Game>();
			// Collection<Gcompany> com = new ArrayList<Gcompany>();
			GPerson persons1 = null;
			Gcompany company1 = null;
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection();
			String sqlCmd = "select * from company ";
			String sqlCmd2 = "select * from person";
			String sqlCmd3 = "select * from game";
			PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Gcompany com = new Gcompany();
				com.setGCname(rs.getString("gCname"));
				com.setId(rs.getLong("id"));
			}
			pstmt.close();
			rs.close();

			PreparedStatement pstmt2 = conn.prepareStatement(sqlCmd2);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				GPerson person = new GPerson();
				person.setId(rs2.getLong("id"));
				person.setpId(rs2.getLong("pId"));
				person.setpName(rs2.getString("pName"));
				person.setpIdno(rs2.getString("pIdno"));

				persons.add(person);

			}

			rs2.close();
			pstmt2.close();

			PreparedStatement pstmt3 = conn.prepareStatement(sqlCmd3);
			ResultSet rs3 = pstmt3.executeQuery();

			while (rs3.next()) {
				Game game = new Game();
				game.setId(rs3.getLong("id"));
				game.setName(rs3.getString("name"));
				game.setType(rs3.getString("type"));
				game.setgId(rs3.getLong("gId"));
				games.add(game);
			}

			rs3.close();
			pstmt3.close();
			conn.close();
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
		// return company1;
		return null;
	}

	@Override
	public void insert(Gcompany entity) {
		
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn;
		try {
			conn = ds.getConnection();
			String sqlCmd = "insert into company values(?,?)";
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet keySet = stmt.getGeneratedKeys();

			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				entity.setId(generatedId);
			}
			
			
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Gcompany entity) {

		try {

			String sqlCmdG = "delete * from game where id = ?";
			String sqlCmdP = "delete * from person where id =?";
			String sqlCmdC = "delete * from company whrere id =?";
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlCmdG);

			String sqlCmd2 = "insert into game values(?,?,?)";
			String sqlCmd3 = "insert into person values(?,?,?)";
			String sqlCmd4 = "insert into company values(?,?)";

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {

		try {
			String sqlCmd = "delete * from game where gId = ?";
			String sqlCmd2 = "delete * from person where pId =?";
			String sqlCmd3 = "delete * from company where id=?";
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);

			pstmt.executeUpdate();

			PreparedStatement pstmt2 = conn.prepareStatement(sqlCmd2);
			pstmt2.setLong(1, id);

			pstmt2.executeUpdate();

			PreparedStatement pstmt3 = conn.prepareStatement(sqlCmd3);
			pstmt3.setLong(1, id);
			pstmt3.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}