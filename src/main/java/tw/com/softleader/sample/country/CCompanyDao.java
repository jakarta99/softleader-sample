package tw.com.softleader.sample.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
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
			String CompanyOneCmd = "SELECT * FROM CCOMPANY WHERE ID=?";
			PreparedStatement stmt1 = conn.prepareStatement(CompanyOneCmd);

			stmt1.setLong(1, id);
			ResultSet rs1 = stmt1.getResultSet();

			if (rs1.next()) {
				cCompany = new CCompany();
				cCompany.setName(rs1.getString("NAME"));

				String PersonOneCmd = "SELECT * FROM CPERSON WHERE C_ID=?";
				PreparedStatement stmt2 = conn.prepareStatement(PersonOneCmd);

				stmt2.setLong(1, id);
				ResultSet rs2 = stmt2.getResultSet();

				while (rs2.next()) {
					cPerson = new CPerson();
					cPerson.setC_ID(id);
					cPerson.setId(rs2.getLong("ID"));
					cPerson.setName(rs2.getString("NAME"));
					cPerson.setIdNo(rs2.getString("IDNO"));

					String CountryOneCmd = "SELECT * FROM COUNTRY WHERE P_ID=?";
					PreparedStatement stmt3 = conn.prepareStatement(CountryOneCmd);

					stmt3.setLong(1, rs2.getLong("ID"));
					ResultSet rs3 = stmt3.getResultSet();

					while (rs3.next()) {
						country = new Country();
						country.setP_ID(rs3.getLong("ID"));
						country.setName(rs3.getString("NAME"));
						country.setSize(rs3.getString("SIZE"));
						
						log.debug("DAO: findOne countryName-->"+rs3.getString("NAME"));
						
						countryList.add(country);
					}

					rs3.close();
					stmt3.close();
					
					log.debug("DAO: findOne cpersonName-->"+rs2.getString("NAME"));
					
					cPerson.setCountries(countryList);
					cpersonList.add(cPerson);
				}

				rs2.close();
				stmt2.close();

				cCompany.setCPersons(cpersonList);
			}
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
			PreparedStatement stmt1 = conn.prepareStatement(CompanyAllCmd);
			ResultSet rs1 = stmt1.getResultSet();

			while (rs1.next()) {
				cCompany = new CCompany();
				cCompany.setId(rs1.getLong("ID"));
				cCompany.setName(rs1.getString("NAME"));

				String PersonAllCmd = "SELECT * FROM CPERSON WHERE C_ID=?";
				PreparedStatement stmt2 = conn.prepareStatement(PersonAllCmd);

				ResultSet rs2 = stmt2.getResultSet();

				while (rs2.next()) {
					cPerson = new CPerson();
					cPerson.setC_ID(rs1.getLong("ID"));
					cPerson.setId(rs2.getLong("ID"));
					cPerson.setName(rs2.getString("NAME"));
					cPerson.setIdNo(rs2.getString("IDNO"));

					String CountryAllCmd = "SELECT * FROM COUNTRY WHERE P_ID=?";
					PreparedStatement stmt3 = conn.prepareStatement(CountryAllCmd);

					stmt3.setLong(1, rs2.getLong("ID"));
					ResultSet rs3 = stmt3.getResultSet();

					while (rs3.next()) {
						country = new Country();
						country.setP_ID(rs3.getLong("ID"));
						country.setName(rs3.getString("NAME"));
						country.setSize(rs3.getString("SIZE"));

						log.debug("DAO: findAll countryName-->"+rs3.getString("NAME"));
						
						countryList.add(country);
					}

					rs3.close();
					stmt3.close();
					
					log.debug("DAO: findAll cpersonName-->"+rs2.getString("NAME"));
					
					cPerson.setCountries(countryList);
					cpersonList.add(cPerson);
				}

				rs2.close();
				stmt2.close();
				
				log.debug("DAO: findAll companyName-->"+rs1.getString("NAME"));
				
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

			Long generatedCc_Id = stmt1.getGeneratedKeys().getLong(1);
			log.debug("DAO: insert generatedCc_Id-->"+generatedCc_Id);

			while (entity.getCPersons().iterator().hasNext()) {
				cPerson = entity.getCPersons().iterator().next();

				String PersonInsertCmd = "INSERT INTO CPERSON (NAME,IDNO,C_ID) VALUES(?,?,?) ";
				PreparedStatement stmt2 = conn.prepareStatement(PersonInsertCmd, Statement.RETURN_GENERATED_KEYS);

				stmt2.setString(1, cPerson.getName());
				stmt2.setString(1, cPerson.getIdNo());
				stmt2.setLong(3, generatedCc_Id);

				Long generatedP_Id = stmt2.getGeneratedKeys().getLong(1);
				log.debug("DAO: insert generatedP_Id-->"+generatedP_Id);
				
				while (cPerson.getCountries().iterator().hasNext()) {
					country = cPerson.getCountries().iterator().next();

					String CountryInsertCmd = "INSERT INTO COUNTRY (NAME,SIZE,P_ID) VALUES(?,?,?)";
					PreparedStatement stmt3 = conn.prepareStatement(CountryInsertCmd, Statement.RETURN_GENERATED_KEYS);

					stmt3.setString(1, "NAME");
					stmt3.setString(2, "SIZE");
					stmt3.setLong(3, generatedP_Id);

					 Long generatedCo_Id =stmt3.getGeneratedKeys().getLong(1);
					 log.debug("DAO: insert generatedCo_Id-->"+generatedCo_Id);

					stmt3.close();
				}
				stmt2.close();
			}
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
	public void update(CCompany entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();

		// String CompanyInsertCmd = "UPDATE CCOMPANY SET NAME=? WHERE ID=?";
		// String PersonInsertCmd = "UPDATE CPERSON SET NAME=?,IDNO=?,C_ID=?
		// WHERE ID=? ";
		// String CountryInsertCmd = "UPDATE COUNTRY SET NAME=?,IDNO=?,P_ID=?
		// WHERE ID=?";
		Connection conn = null;

		try {
			conn = ds.getConnection();
			String CountryDeleteCmd = "DELETE FROM COUNTRY WHERE ID=?";
			PreparedStatement stmt1 = conn.prepareStatement(CountryDeleteCmd);
			stmt1.setLong(1, entity.getCPersons().iterator().next().getCountries().iterator().next().getP_ID());
			stmt1.close();

			String PersonDeleteCmd = "DELETE FROM CPERSON WHERE ID=?";
			PreparedStatement stmt2 = conn.prepareStatement(PersonDeleteCmd);
			stmt2.setLong(1, entity.getCPersons().iterator().next().getC_ID());
			stmt2.close();

			String CompanyDeleteCmd = "DELETE FROM CCOMPANY WHERE ID=?";
			PreparedStatement stmt3 = conn.prepareStatement(CompanyDeleteCmd);
			stmt3.setLong(1, entity.getId());
			stmt3.close();
			
			log.debug("DAO: update companyList.Size-->"+ccompanyList.size());
			
			String CompanyInsertCmd = "INSERT INTO CCOMPANY (NAME) VALUES(?)";
			PreparedStatement stmt4 = conn.prepareStatement(CompanyInsertCmd, Statement.RETURN_GENERATED_KEYS);
			stmt4.setString(1, entity.getName());

			Long generatedCc_Id = stmt4.getGeneratedKeys().getLong(1);
			log.debug("DAO: update generatedCc_Id-->"+generatedCc_Id);

			while (entity.getCPersons().iterator().hasNext()) {
				cPerson = entity.getCPersons().iterator().next();

				String PersonInsertCmd = "INSERT INTO CPERSON (NAME,IDNO,C_ID) VALUES(?,?,?) ";
				PreparedStatement stmt5 = conn.prepareStatement(PersonInsertCmd, Statement.RETURN_GENERATED_KEYS);

				stmt5.setString(1, cPerson.getName());
				stmt5.setString(1, cPerson.getIdNo());
				stmt5.setLong(3, generatedCc_Id);

				Long generatedP_Id = stmt2.getGeneratedKeys().getLong(1);
				log.debug("DAO: update generatedP_Id-->"+generatedP_Id);

				while (cPerson.getCountries().iterator().hasNext()) {
					country = cPerson.getCountries().iterator().next();

					String CountryInsertCmd = "INSERT INTO COUNTRY (NAME,SIZE,P_ID) VALUES(?,?,?)";
					PreparedStatement stmt6 = conn.prepareStatement(CountryInsertCmd, Statement.RETURN_GENERATED_KEYS);

					stmt6.setString(1, "NAME");
					stmt6.setString(2, "SIZE");
					stmt6.setLong(3, generatedP_Id);

					Long generatedCo_Id =stmt3.getGeneratedKeys().getLong(1);
					log.debug("DAO: insert generatedCo_Id-->"+generatedCo_Id);

					stmt6.close();
				}
				stmt5.close();
			}
			stmt4.close();

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
			String CountryDeleteCmd = "TRUNCATE TABLE COUNTRY";
			PreparedStatement stmt1 = conn.prepareStatement(CountryDeleteCmd);
			stmt1.close();

			String PersonDeleteCmd = "TRUNCATE TABLE CPERSON";
			PreparedStatement stmt2 = conn.prepareStatement(PersonDeleteCmd);
			stmt2.close();

			String CompanyDeleteCmd = "DELETE FROM CCOMPANY WHERE ID=?";
			PreparedStatement stmt3 = conn.prepareStatement(CompanyDeleteCmd);
			stmt3.setLong(1, id);
			stmt3.close();
			
			log.debug("DAO: delete companyList.Size"+ccompanyList.size());

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