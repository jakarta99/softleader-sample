package tw.com.softleader.sample.KPerson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.cloth.Cloth;
import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class KCompanyDao implements GenericDao<KCompany> {

	private Logger log = LoggerFactory.getLogger(KCompanyDao.class);
	private DataSource ds;
	@Override
	public KCompany findOne(Long id) {
		KCompany entity = null;
		List<KPerson> emps = new ArrayList<>();

		try {

//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = getDs().getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM K_COMPANY WHERE COMPANY_ID = " + id;

			log.debug("1:" + sqlCmd);
			log.info("2:" + sqlCmd);
			log.warn("3:" + sqlCmd);
			log.error("4:" + sqlCmd);

			ResultSet rs = stmt.executeQuery(sqlCmd);

			if (rs.next()) {

				entity = new KCompany();
				entity.setId(rs.getLong("company_id"));
				entity.setName(rs.getString("company_name"));
				entity.setEmps(emps);
			}

			sqlCmd = "SELECT * FROM K_PERSON WHERE company = " + id;
			rs = stmt.executeQuery(sqlCmd);
			KPerson kPerson = null;
			while (rs.next()) {
				kPerson = new KPerson();
				kPerson.setId(rs.getLong("person_id"));
				kPerson.setCompanyId(rs.getLong("company"));
				kPerson.setName(rs.getString("person_name"));
				kPerson.setIdNo(rs.getString("id_no"));
				emps.add(kPerson);

			}
			for (KPerson person : emps) {
				sqlCmd = "SELECT * FROM CLOTH WHERE OWNER_ID = " + person.getId();
				rs = stmt.executeQuery(sqlCmd);
				List<Cloth> clothes = new ArrayList<>();
				Cloth cloth = null;
				while (rs.next()) {
					cloth = new Cloth();
					cloth.setId(rs.getLong("cloth_id"));
					cloth.setColor(rs.getString("color"));
					cloth.setOwnerId(rs.getLong("owner_Id"));
					cloth.setName(rs.getString("cloth_name"));
					clothes.add(cloth);

				}
				person.setClothes(clothes);
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
	public Collection<KCompany> findAll() {

		Collection<KCompany> companys = new ArrayList<KCompany>();

		try {
//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = getDs().getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "SELECT * FROM K_COMPANY";

			ResultSet rs = stmt.executeQuery(sqlCmd);

			while (rs.next()) {

				KCompany entity = new KCompany();
				entity.setId(rs.getLong("company_id"));
				entity.setName(rs.getString("company_name"));

				companys.add(entity);

			}

			for (KCompany company : companys) {
				sqlCmd = "SELECT * FROM K_PERSON where company =" + company.getId();
				rs = stmt.executeQuery(sqlCmd);
				Collection<KPerson> persons = new ArrayList<>();
				while (rs.next()) {

					KPerson entity = new KPerson();
					entity.setId(rs.getLong("person_id"));
					entity.setName(rs.getString("person_name"));
					entity.setIdNo(rs.getString("id_No"));

					persons.add(entity);

				}
				company.setEmps(persons);

				for (KPerson person : persons) {
					sqlCmd = "SELECT * FROM CLOTH where owner_id =" + person.getId();
					rs = stmt.executeQuery(sqlCmd);
					Collection<Cloth> clothes = new ArrayList<>();
					while (rs.next()) {

						Cloth entity = new Cloth();
						entity.setId(rs.getLong("cloth_id"));
						entity.setName(rs.getString("cloth_name"));
						entity.setColor(rs.getString("color"));
						entity.setOwnerId(person.getId());

						clothes.add(entity);

					}
					person.setClothes(clothes);
				}

			}

			rs.close();

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return companys;
	}

	@Override
	public void insert(KCompany entity) {

		try {
//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = getDs().getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "INSERT INTO K_COMPANY (company_name) VALUES ('" + entity.getName() + "');";

			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet keySet = stmt.getGeneratedKeys();
			Long companyGeneratedId = 0L;
			if (keySet.next()) {
				companyGeneratedId = keySet.getLong("COMPANY_ID");
				entity.setId(companyGeneratedId);
			}
			if (entity.getEmps() != null) {
				for (KPerson person : entity.getEmps()) {
					sqlCmd = "INSERT INTO K_PERSON (person_name, id_no,company) VALUES ('" + person.getName() + "','"
							+ person.getIdNo() + "','" + companyGeneratedId + "');";
					stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
					keySet = stmt.getGeneratedKeys();
					Long personGeneratedId = 0L;
					if (keySet.next()) {
						personGeneratedId = keySet.getLong("PERSON_ID");
						entity.setId(personGeneratedId);
					}
					Collection<Cloth> clothes = person.getClothes();
					for (Cloth c : clothes) {
						sqlCmd = "INSERT INTO CLOTH (cloth_name, color,owner_id) VALUES ('" + c.getName() + "','"
								+ c.getColor() + "','" + personGeneratedId + "');";

						stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

						keySet = stmt.getGeneratedKeys();

						if (keySet.next()) {
							Long generatedId = keySet.getLong("CLOTH_ID");
							entity.setId(generatedId);
						}
					}

				}

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
	public void update(KCompany entity) {
		try {
//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = getDs().getConnection();

			Statement stmt = connection.createStatement();

			String sqlCmd = "UPDATE K_COMPANY SET " + "company_name = '" + entity.getName() + "' "
					+ "WHERE company_ID = " + entity.getId();

			stmt.executeUpdate(sqlCmd);
			if (entity.getEmps() != null && !entity.getEmps().isEmpty()) {
				KPerson kp = entity.getEmps().stream().findAny().get();
				sqlCmd = "UPDATE K_PERSON SET " + "person_name = '" + kp.getName() + "', " + "id_no = '" + kp.getIdNo()
						+ "'  " + "WHERE company = " + entity.getId();
				stmt.executeUpdate(sqlCmd);

				if (kp.getClothes() != null && !kp.getClothes().isEmpty()) {
					Cloth cloth = kp.getClothes().stream().findAny().get();
					sqlCmd = "UPDATE CLOTH SET " + "cloth_name = '" + cloth.getName() + "', " + "color = '"
							+ cloth.getColor() + "'  " + "WHERE OWNER_ID = " + kp.getId();

					stmt.executeUpdate(sqlCmd);
				}

			}

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {
		try {
//			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = getDs().getConnection();
			Statement stmt = connection.createStatement();
			KCompany company = findOne(id);
			String sqlCmd;
			for (KPerson person : company.getEmps()) {
				sqlCmd = "DELETE FROM CLOTH WHERE OWNER_ID = " + person.getId();
				stmt.executeUpdate(sqlCmd);

				sqlCmd = "DELETE FROM K_PERSON WHERE PERSON_ID = " + person.getId();

				stmt.executeUpdate(sqlCmd);
			}

			sqlCmd = "DELETE FROM K_Company WHERE COMPANY_ID = " + id;

			stmt.executeUpdate(sqlCmd);

			stmt.close();

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
