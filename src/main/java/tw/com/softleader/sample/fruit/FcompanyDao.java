package tw.com.softleader.sample.fruit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;

//import org.apache.log4j.Logger;
import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class FcompanyDao implements GenericDao<Fcompany> {

//	private Logger log = Logger.getLogger(this.getClass());

	private Fcompany fcompany;

	private Fman fman;

	private Fruit fruit;

	@Override
	public Fcompany findOne(Long id) {
		Collection<Fman> fmans = new ArrayList<>();
		Collection<Fruit> fruits = new ArrayList<>();
		fcompany = null;
		fman = null;
		fruit = null;

		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdc = "select * from Fcompany where id = " + id;
		String sqlcmdp = "select * from Fman where cid = " + id;

		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlcmdc);

			while (rs.next()) {
				Statement stmt2 = connection.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sqlcmdp);
				while (rs2.next()) {
					String sqlcmdf = "select * from Fruit where pid = " + rs2.getLong("id");
					Statement stmt3 = connection.createStatement();
					ResultSet rs3 = stmt3.executeQuery(sqlcmdf);
					while (rs3.next()) {
						fruit = new Fruit();
						fruit.setId(rs3.getLong("id"));
						fruit.setColor(rs3.getString("color"));
						fruit.setName(rs3.getString("name"));
						fruits.add(fruit);

					}

					fman = new Fman();
					fman.setId(rs2.getLong("id"));
					fman.setIDno(rs2.getString("idNo"));
					fman.setName(rs2.getString("name"));
					fman.setFruits(fruits);
					fmans.add(fman);

					rs3.close();
					stmt3.close();
				}

				fcompany = new Fcompany();
				fcompany.setId(rs.getLong("id"));
				fcompany.setUno(rs.getString("Uno"));
				fcompany.setName(rs.getString("name"));
				fcompany.setFmans(fmans);

				rs2.close();
				stmt2.close();

			}

			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fcompany;
	}

	@Override
	public Collection<Fcompany> findAll() {
		Collection<Fcompany> fcompanies = new ArrayList<>();
		Collection<Fman> fmans = new ArrayList<>();
		Collection<Fruit> fruits = new ArrayList<>();
		fcompany = null;
		fman = null;
		fruit = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdc = "select * from Fcompany";

		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlcmdc);

			while (rs.next()) {
				fcompany = new Fcompany();
				fcompany.setId(rs.getLong("id"));
				fcompany.setUno(rs.getString("Uno"));
				fcompany.setName(rs.getString("name"));

				String sqlcmdp = "select * from Fman where cid = " + rs.getLong("id");
				Statement stmt2 = connection.createStatement();
				ResultSet rs2 = stmt2.executeQuery(sqlcmdp);
				while (rs2.next()) {

					fman = new Fman();
					fman.setId(rs2.getLong("id"));
					fman.setIDno(rs2.getString("IDno"));
					fman.setName(rs2.getString("name"));

					String sqlcmdf = "select * from Fruit where pid = " + rs2.getLong("id");
					Statement stmt3 = connection.createStatement();
					ResultSet rs3 = stmt3.executeQuery(sqlcmdf);
					while (rs3.next()) {

						fruit = new Fruit();
						fruit.setId(rs3.getLong("id"));
						fruit.setColor(rs3.getString("color"));
						fruit.setName(rs3.getString("name"));
						fruits.add(fruit);
						fman.setFruits(fruits);
					}
					fmans.add(fman);
					fcompany.setFmans(fmans);
					rs3.close();
					stmt3.close();
				}
				fcompanies.add(fcompany);
				rs2.close();
				stmt2.close();
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fcompanies;
	}

	@Override
	public void insert(Fcompany entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlCmdc = "insert into fcompany(Uno,name)values(?,?)";

		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlCmdc, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, entity.getUno());
			pstmt.setString(2, entity.getName());
			pstmt.executeUpdate();
			ResultSet keyset = pstmt.getGeneratedKeys();
			if (keyset.next()) {
				Long generatedId = keyset.getLong("id");
				entity.setId(generatedId);
			}

			if (entity.getFmans() != null) {

				for (Fman fman : entity.getFmans()) {

					String sqlcmdp = "insert into fman(name,idno,cid)values(?,?,?)";
					PreparedStatement pstmt2 = connection.prepareStatement(sqlcmdp, Statement.RETURN_GENERATED_KEYS);
					pstmt2.setString(1, fman.getName());
					pstmt2.setString(2, fman.getIDno());
					pstmt2.setLong(3, entity.getId());
					pstmt2.executeUpdate();
					ResultSet keyset2 = pstmt2.getGeneratedKeys();
					if (keyset2.next()) {
						Long generatedId2 = keyset2.getLong("id");
						fman.setId(generatedId2);
					}
					if (fman.getFruits() != null) {

						String sqlcmdf = "insert into fruit(name,color,pid)values(?,?,?)";
						PreparedStatement pstmt3 = connection.prepareStatement(sqlcmdf);
						for (Fruit fruit : fman.getFruits()) {

							pstmt3.setString(1, fruit.getName());
							pstmt3.setString(2, fruit.getColor());
							pstmt3.setLong(3, fman.getId());
							pstmt3.executeUpdate();
						}
						keyset2.close();
						pstmt3.close();
						pstmt2.close();
					}
				}
			}
			keyset.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Fcompany entity) {
		FcompanyDao fcompanyDao = new FcompanyDao();
		fcompanyDao.delete(entity.getId());
		fcompanyDao.insert(entity);

	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		String sqlcmdc = "delete from Fcompany where id =" + id;
		String sqlcmdp = "delete from Fman where cid =" + id;
		FcompanyDao fcompanyDao = new FcompanyDao();

		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();

			Collection<Fcompany> fcompanies = fcompanyDao.findAll();

			for (Fcompany fcompany : fcompanies) {
				if (fcompany.getFmans() != null) {

					for (Fman fman : fcompany.getFmans()) {
						String sqlcmdf = "delete from Fruit where pid =" + fman.getId();
						Statement stmt3 = connection.createStatement();
						stmt3.executeUpdate(sqlcmdf);
						stmt3.close();
					}
				}
			}
			stmt2.executeUpdate(sqlcmdp);
			stmt.executeUpdate(sqlcmdc);

			stmt2.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
