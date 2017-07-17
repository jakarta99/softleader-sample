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

public class GcompanyDao implements GenericDao<Gcompany> {
	Collection<GPerson> gpersons = new ArrayList<GPerson>();
	Collection<Game> games = new ArrayList<Game>();
	Collection<Gcompany> company = new ArrayList<Gcompany>();

	// @SuppressWarnings("null")
	@Override
	public Gcompany findOne(Long id) {
		try {

			// GPerson persons1 = null;
			// Gcompany company1 = null;
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection(); // set connection
			String sqlCmd = "select * from company where id =?"; // SQL
																	// statements
			String sqlCmd2 = "select * from person where pId =?";
			String sqlCmd3 = "select * from ame where gId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlCmd); // declare
																		// Prepared
																		// Statements
			ResultSet rs = pstmt.executeQuery(); // execute statement and
													// instantiate variable rs
			pstmt.setLong(1, id); // set dynamic variable

			if (rs.next()) { // point to the next row
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
				gpersons.add(person);

			}
			((Gcompany) company).setGpersons(gpersons);
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
			/// persons1.setGames(games);
			((Gcompany) company).setGames(games);

			pstmt3.close();
			rs3.close();
			conn.close();

		} catch (

		SQLException e) {

			e.printStackTrace();
		}
		return (Gcompany) company;

	}

	@Override
	public Collection<Gcompany> findAll() {

		try {
			// GPerson persons1 = null;
			// Gcompany company1 = null;
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

				gpersons.add(person);
				((Gcompany) company).setGpersons(gpersons);
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
				((Gcompany) company).setGames(games);
			}

			rs3.close();
			pstmt3.close();
			conn.close();
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
		return company;

	}

	@Override
	public void insert(Gcompany entity) {

		DataSource ds = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection conn = ds.getConnection();

			String sqlCmd = "insert into company (id,gCname)values(?,?)";
			String sqlCmd2 = "insert into person (pId,pName,pIdno)values(?,?,?)";
			String sqlCmd3 = "insert into game (gId,gName,type)values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sqlCmd);

			pstmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet keySet = pstmt.getGeneratedKeys();

			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				String generatedName = keySet.getNString("gCname");

				entity.setId(generatedId);
				entity.setGCname(generatedName);

			}

			PreparedStatement pstmt2 = conn.prepareStatement(sqlCmd2);
			ResultSet keySet2 = pstmt2.getGeneratedKeys();
			pstmt2.execute(sqlCmd2, Statement.RETURN_GENERATED_KEYS);

			if (keySet2.next()) {

				GPerson person = new GPerson();
				Long generatedpId = keySet.getLong("pId");
				String generatedpName = keySet.getString("pName");
				String generatedIdno = keySet.getString("pIdno");
				person.setpId(generatedpId);
				person.setpName(generatedpName);
				person.setpIdno(generatedIdno);
				gpersons.add(person);
				entity.setGpersons(gpersons);
			}

			PreparedStatement pstmt3 = conn.prepareStatement(sqlCmd3);
			ResultSet keySet3 = pstmt3.getGeneratedKeys();
			pstmt3.execute(sqlCmd3, Statement.RETURN_GENERATED_KEYS);

			if (keySet3.next()) {

				Game game = new Game();
				Long generatedgId = keySet3.getLong("gId");
				String generatedName = keySet3.getString("gName");
				String generatedType = keySet3.getString("type");
				game.setgId(generatedgId);
				game.setName(generatedName);
				game.setType(generatedType);
				games.add(game);
				entity.setGames(games);

			}

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Gcompany entity) {

		try {

			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection conn = ds.getConnection();
            
			GcompanyDao gcDao = new GcompanyDao();
			gcDao.delete(entity.getId());
			gcDao.insert(entity);
			conn.close();
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