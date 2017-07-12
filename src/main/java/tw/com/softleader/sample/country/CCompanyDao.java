package tw.com.softleader.sample.country;

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

public class CCompanyDao implements GenericDao<CCompany> {
	private Logger log = Logger.getLogger(this.getClass());

	Collection<CCompany> ccompanyList = new ArrayList<CCompany>();
	Collection<CPerson> cpersonList = new ArrayList<CPerson>();
	Collection<Country> countryList = new ArrayList<Country>();
	CCompany cCompany = null;
	CPerson cPerson = null;
	Country country = null;

	@Override
	public CCompany findOne(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn = null;

		try {
			conn = ds.getConnection();
			String CompanyOneCmd = "SELECT * FROM CCOMPANY WHERE ID=" + id;
			Statement stmt1 = conn.createStatement();

			ResultSet rs1 = stmt1.executeQuery(CompanyOneCmd);

			if (rs1.next()) {
				cCompany = new CCompany();
				cCompany.setName(rs1.getString("NAME"));
				cCompany.setId(rs1.getLong("ID"));
			}
			String PersonOneCmd = "SELECT * FROM CPERSON WHERE C_ID=" + id;
			Statement stmt2 = conn.createStatement();

			ResultSet rs2 = stmt2.executeQuery(PersonOneCmd);

			while (rs2.next()) {
				cPerson = new CPerson();
				cPerson.setC_ID(rs1.getLong("ID"));
				cPerson.setId(rs2.getLong("ID"));
				cPerson.setName(rs2.getString("NAME"));
				cPerson.setIdNo(rs2.getString("IDNO"));

				String CountryOneCmd = "SELECT * FROM COUNTRY WHERE P_ID=" + rs2.getLong("ID");
				Statement stmt3 = conn.createStatement();

				ResultSet rs3 = stmt3.executeQuery(CountryOneCmd);

				while (rs3.next()) {
					country = new Country();
					country.setP_ID(rs2.getLong("ID"));
					country.setName(rs3.getString("NAME"));
					country.setSize(rs3.getString("SIZE"));
					country.setId(rs3.getLong("ID"));

					log.debug("DAO: findOne countryName-->" + rs3.getString("NAME"));

					countryList.add(country);
				}

				rs3.close();
				stmt3.close();

				log.debug("DAO: findOne cpersonName-->" + rs2.getString("NAME"));

				cPerson.setCountries(countryList);
				cpersonList.add(cPerson);
			}

			rs2.close();
			stmt2.close();

			cCompany.setCPersons(cpersonList);

			rs1.close();
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cCompany;
	}

	@Override
	public Collection<CCompany> findAll() {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn = null;

		try {
			conn = ds.getConnection();
			String CompanyAllCmd = "SELECT * FROM CCOMPANY";
			Statement stmt1 = conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery(CompanyAllCmd);

			while (rs1.next()) {
				cCompany = new CCompany();
				cCompany.setId(rs1.getLong("ID"));
				cCompany.setName(rs1.getString("NAME"));

				String PersonAllCmd = "SELECT * FROM CPERSON WHERE C_ID=" + rs1.getString("ID");
				Statement stmt2 = conn.createStatement();

				ResultSet rs2 = stmt2.executeQuery(PersonAllCmd);

				while (rs2.next()) {
					cPerson = new CPerson();
					cPerson.setC_ID(rs1.getLong("ID"));
					cPerson.setId(rs2.getLong("ID"));
					cPerson.setName(rs2.getString("NAME"));
					cPerson.setIdNo(rs2.getString("IDNO"));

					String CountryAllCmd = "SELECT * FROM COUNTRY WHERE P_ID=" + rs2.getString("ID");
					Statement stmt3 = conn.createStatement();

					ResultSet rs3 = stmt3.executeQuery(CountryAllCmd);

					while (rs3.next()) {
						country = new Country();
						country.setP_ID(rs3.getLong("ID"));
						country.setName(rs3.getString("NAME"));
						country.setSize(rs3.getString("SIZE"));

						log.debug("DAO: findAll countryName-->" + rs3.getString("NAME"));

						countryList.add(country);
					}

					rs3.close();
					stmt3.close();

					log.debug("DAO: findAll cpersonName-->" + rs2.getString("NAME"));

					cPerson.setCountries(countryList);
					cpersonList.add(cPerson);
				}

				rs2.close();
				stmt2.close();

				log.debug("DAO: findAll companyName-->" + rs1.getString("NAME"));

				cCompany.setCPersons(cpersonList);
			}
			ccompanyList.add(cCompany);
			rs1.close();
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return ccompanyList;
	}

	@Override
	public void insert(CCompany entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn = null;

		try {
			conn = ds.getConnection();
			String CompanyInsertCmd = "INSERT INTO CCOMPANY (NAME) VALUES(?)";
			PreparedStatement stmt1 = conn.prepareStatement(CompanyInsertCmd, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, entity.getName());
			stmt1.executeUpdate();

			ResultSet keySet1 = stmt1.getGeneratedKeys();
			Long generatedCc_Id = null;
			if (keySet1.next()) {
				generatedCc_Id = keySet1.getLong(1);
				cCompany.setId(generatedCc_Id);
				entity.setId(generatedCc_Id);
				log.debug("DAO: insert generatedCc_Id-->" + generatedCc_Id);
			}
			Iterator<CPerson> cPersonInsert = entity.getCPersons().iterator();
			while (cPersonInsert.hasNext()) {
				cPerson = cPersonInsert.next();
				String PersonInsertCmd = "INSERT INTO CPERSON (NAME,IDNO,C_ID) VALUES(?,?,?) ";
				PreparedStatement stmt2 = conn.prepareStatement(PersonInsertCmd, Statement.RETURN_GENERATED_KEYS);

				stmt2.setString(1, cPerson.getName());
				stmt2.setString(2, cPerson.getIdNo());
				stmt2.setLong(3, cCompany.getId());
				stmt2.executeUpdate();

				ResultSet keySet2 = stmt2.getGeneratedKeys();
				Long generatedP_Id = null;
				if (keySet2.next()) {
					generatedP_Id = keySet2.getLong(1);
					cPerson.setId(generatedP_Id);
					cPerson.setC_ID(generatedCc_Id);
					entity.getCPersons().iterator().next().setId(generatedP_Id);
					entity.getCPersons().iterator().next().setC_ID(generatedCc_Id);
					log.debug("DAO: insert generatedP_Id-->" + generatedP_Id);
				}

				Iterator<Country> countryInsert = cPerson.getCountries().iterator();
				while (countryInsert.hasNext()) {
					country = countryInsert.next();

					String CountryInsertCmd = "INSERT INTO COUNTRY (NAME,SIZE,P_ID) VALUES(?,?,?)";
					PreparedStatement stmt3 = conn.prepareStatement(CountryInsertCmd, Statement.RETURN_GENERATED_KEYS);

					stmt3.setString(1, country.getName());
					stmt3.setString(2, country.getSize());
					stmt3.setLong(3, cPerson.getId());
					stmt3.executeUpdate();

					country.setP_ID(generatedP_Id);

					ResultSet keySet3 = stmt3.getGeneratedKeys();
					Long generatedCo_Id = null;
					if (keySet3.next()) {
						generatedCo_Id = keySet3.getLong(1);
						country.setId(generatedCo_Id);
						country.setP_ID(generatedP_Id);
						entity.getCPersons().iterator().next().getCountries().iterator().next().setP_ID(generatedP_Id);
						entity.getCPersons().iterator().next().getCountries().iterator().next().setId(generatedCo_Id);
						;
						log.debug("DAO: insert generatedCo_Id-->" + generatedCo_Id);
					}
					keySet3.close();
					stmt3.close();
				}
				keySet2.close();
				stmt2.close();
			}
			keySet1.close();
			stmt1.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void update(CCompany entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn = null;

		try {
			conn = ds.getConnection();
			String CountryUpdateDel = "DELETE FROM COUNTRY WHERE P_ID=?";
			PreparedStatement stmt1 = conn.prepareStatement(CountryUpdateDel);
			stmt1.setLong(1, entity.getCPersons().iterator().next().getCountries().iterator().next().getP_ID());
			log.debug("DAO: update Del P_ID-->"
					+ entity.getCPersons().iterator().next().getCountries().iterator().next().getP_ID());
			stmt1.executeUpdate();
			stmt1.close();

			String PersonUpdateDel = "DELETE FROM CPERSON WHERE C_ID=?";
			PreparedStatement stmt2 = conn.prepareStatement(PersonUpdateDel);
			stmt2.setLong(1, entity.getId());
			stmt2.executeUpdate();
			stmt2.close();

			String CompanyUpdateDel = "DELETE FROM CCOMPANY WHERE ID=?";
			PreparedStatement stmt3 = conn.prepareStatement(CompanyUpdateDel);
			stmt3.setLong(1, entity.getId());
			stmt3.executeUpdate();
			stmt3.close();

			String CompanyUpdateIns = "INSERT INTO CCOMPANY (NAME) VALUES(?)";
			PreparedStatement stmt4 = conn.prepareStatement(CompanyUpdateIns, Statement.RETURN_GENERATED_KEYS);
			stmt4.setString(1, entity.getName());
			stmt4.executeUpdate();

			ResultSet keySet4 = stmt4.getGeneratedKeys();
			Long generatedCc_Id = null;
			if (keySet4.next()) {
				generatedCc_Id = keySet4.getLong(1);
				cCompany.setId(generatedCc_Id);
				entity.setId(generatedCc_Id);
				log.debug("DAO: update Ins generatedCc_Id-->" + generatedCc_Id);
			}
			Iterator<CPerson> cPersonInsert = entity.getCPersons().iterator();
			while (cPersonInsert.hasNext()) {
				CPerson cPerson = new CPerson();
				cPerson = cPersonInsert.next();

				String PersonUpdateIns = "INSERT INTO CPERSON (NAME,IDNO,C_ID) VALUES(?,?,?) ";
				PreparedStatement stmt5 = conn.prepareStatement(PersonUpdateIns, Statement.RETURN_GENERATED_KEYS);

				stmt5.setString(1, cPerson.getName());
				stmt5.setString(2, cPerson.getIdNo());
				stmt5.setLong(3, cCompany.getId());
				stmt5.executeUpdate();

				ResultSet keySet5 = stmt5.getGeneratedKeys();
				this.cPerson = cPerson;
				Long generatedP_Id = null;
				if (keySet5.next()) {
					generatedP_Id = keySet5.getLong(1);
					cPerson.setId(generatedP_Id);
					cPerson.setC_ID(generatedCc_Id);
					entity.getCPersons().iterator().next().setId(generatedP_Id);
					entity.getCPersons().iterator().next().setC_ID(generatedCc_Id);
					log.debug("DAO: update Ins generatedP_Id-->" + generatedP_Id);
				}

				Iterator<Country> countryInsert = entity.getCPersons().iterator().next().getCountries().iterator();
				while (countryInsert.hasNext()) {
					Country country = new Country();
					country = countryInsert.next();

					String CountryUpdateIns = "INSERT INTO COUNTRY (NAME,SIZE,P_ID) VALUES(?,?,?)";
					PreparedStatement stmt6 = conn.prepareStatement(CountryUpdateIns, Statement.RETURN_GENERATED_KEYS);

					stmt6.setString(1, country.getName());
					stmt6.setString(2, country.getSize());
					stmt6.setLong(3, cPerson.getId());
					stmt6.executeUpdate();

					country.setP_ID(generatedP_Id);

					ResultSet keySet6 = stmt6.getGeneratedKeys();
					Long generatedCo_Id = null;
					if (keySet6.next()) {
						generatedCo_Id = keySet6.getLong(1);
						country.setId(generatedCo_Id);
						country.setP_ID(generatedP_Id);
						entity.getCPersons().iterator().next().getCountries().iterator().next().setP_ID(generatedP_Id);
						entity.getCPersons().iterator().next().getCountries().iterator().next().setId(generatedCo_Id);
						;
						log.debug("DAO: update Ins generatedCo_Id-->" + generatedCo_Id);
					}
					keySet6.close();
					stmt3.close();
				}
				keySet5.close();
				stmt2.close();
			}
			keySet4.close();
			stmt1.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void delete(Long id) {

		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Connection conn = null;
		try {
			conn = ds.getConnection();
			String CountryDeleteCmd = "DROP TABLE COUNTRY";
			PreparedStatement stmt1 = conn.prepareStatement(CountryDeleteCmd);
			stmt1.executeUpdate();
			stmt1.close();

			String PersonDeleteCmd = "DROP TABLE CPERSON";
			PreparedStatement stmt2 = conn.prepareStatement(PersonDeleteCmd);
			stmt2.executeUpdate();
			stmt2.close();

			String CompanyDeleteCmd = "DELETE FROM CCOMPANY WHERE ID=?";
			PreparedStatement stmt3 = conn.prepareStatement(CompanyDeleteCmd);
			log.debug("DAO: delete Delta ID" + id);
			stmt3.setLong(1, id);
			stmt3.executeUpdate();
			stmt3.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}