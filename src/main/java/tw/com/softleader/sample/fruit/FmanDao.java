package tw.com.softleader.sample.fruit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class FmanDao implements GenericDao<Fman> {
	
	private Logger log = Logger.getLogger(this.getClass());

	private Fman fman;

	private Fruit fruit;

	@Override
	public Fman findOne(Long id) {
		Collection<Fruit> fruits = new ArrayList<>();
		fman = null;
		fruit = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdp = "select * from Fman where id = " + id;
		String sqlcmdf = "select * from Fruit where pid = " + id;

		try {
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlcmdp);
			Statement statement2 = connection.createStatement();
			ResultSet rs2 = statement2.executeQuery(sqlcmdf);


			
			
			while (rs.next()) {
				while (rs2.next()) {
					fruit = new Fruit();
					fruit.setId(rs2.getLong("id"));
					fruit.setColor(rs2.getString("color"));
					fruit.setName(rs2.getString("name"));
					fruits.add(fruit);
					log.debug("findone"+rs2.getLong("id"));
				}
				fman = new Fman();
				fman.setId(rs.getLong("id"));
				fman.setIDno(rs.getString("idNo"));
				fman.setName(rs.getString("name"));
				fman.setFruits(fruits);
			}
			rs.close();
			rs2.close();
			statement.close();
			statement2.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fman;
	}

	@Override
	public Collection<Fman> findAll() {
		Collection<Fruit> fruits = new ArrayList<>();
		Collection<Fman> fmans = new ArrayList<>();
		fman = null;
		fruit = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdp = "select * from Fman";

		try {
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sqlcmdp);

			while (rs.next()) {
				fman = new Fman();
				fman.setId(rs.getLong("id"));
				fman.setIDno(rs.getString("idNo"));
				fman.setName(rs.getString("name"));

				String sqlcmdf = "select * from Fruit where pid = " + rs.getLong("id");
				Statement statement2 = connection.createStatement();
				ResultSet rs2 = statement2.executeQuery(sqlcmdf);

				while (rs2.next()) {
					fruit = new Fruit();
					fruit.setId(rs2.getLong("id"));
					fruit.setColor(rs2.getString("color"));
					fruit.setName(rs2.getString("name"));
					fruits.add(fruit);
					fman.setFruits(fruits);
				}
				rs2.close();
				statement2.close();

				fmans.add(fman);
			}
			rs.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fmans;
	}

	@Override
	public void insert(Fman entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmdp = "insert into fman(idNo,name)values(?,?)";

		try {
			Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlCmdp, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, entity.getIDno());
			pstmt.setString(2, entity.getName());
			pstmt.executeUpdate();
			ResultSet keyset = pstmt.getGeneratedKeys();
			if (keyset.next()) {
				Long generatedId = keyset.getLong("id");
				entity.setId(generatedId);
			}

			String sqlcmdf = "insert into fruit(name,color,pid)values(?,?,?)";
			PreparedStatement pstmt2 = connection.prepareStatement(sqlcmdf);

			Iterator<Fruit> fruitinsert = entity.getFruits().iterator();
			if (fruitinsert.hasNext()) {
//				if (entity.getFruits()!=null) {
				for (Fruit fruit : entity.getFruits()) {

					pstmt2.setString(1, fruit.getName());
					pstmt2.setString(2, fruit.getColor());
					pstmt2.setLong(3, entity.getId());
					pstmt2.executeUpdate();
				}
			}

			pstmt2.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Fman entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = ds.getConnection();
			String sqlcmddp = "delete from Fman where id =" + entity.getId();
			String sqlcmddf = "delete from Fruit where pid =" + entity.getId();
			String sqlCmdip = "insert into fman(idNo,name)values(?,?)";
			String sqlCmdif = "insert into fruit(name,color,pid)values(?,?,?)";
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			PreparedStatement pstmt = connection.prepareStatement(sqlCmdip, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pstmt2 = connection.prepareStatement(sqlCmdif);
			stmt2.executeUpdate(sqlcmddf);
			stmt.executeUpdate(sqlcmddp);

			pstmt.setString(1, entity.getIDno());
			pstmt.setString(2, entity.getName());
			pstmt.executeUpdate();

			ResultSet keyset = pstmt.getGeneratedKeys();
			if (keyset.next()) {
				Long generatedId = keyset.getLong("id");
				entity.setId(generatedId);
			}

//			Iterator<Fruit> fruitinsert = entity.getFruits().iterator();

			if (entity.getFruits()!= null) {
				for (Fruit fruit : entity.getFruits()) {
					pstmt2.setString(1, fruit.getName());
					pstmt2.setString(2, fruit.getColor());
					pstmt2.setLong(3, entity.getId());
					pstmt2.executeUpdate();
				}
			} 
			else {//如果為空值
				String sqlcmdddf = "delete from Fruit where pid =" + entity.getId();
				Statement stmt3 = connection.createStatement();
				stmt3.executeQuery(sqlcmdddf);
				
				stmt3.close();
			}

			
			stmt2.close();
			stmt.close();
			pstmt2.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdp = "delete from Fman where id =" + id;
		String sqlcmdf = "delete from Fruit where pid =" + id;

		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			stmt2.executeUpdate(sqlcmdf);
			stmt.executeUpdate(sqlcmdp);

			stmt2.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
