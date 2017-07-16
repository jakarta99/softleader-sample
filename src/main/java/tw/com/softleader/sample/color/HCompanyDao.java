package tw.com.softleader.sample.color;

import java.sql.Connection;
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

public class HCompanyDao implements GenericDao<HCompany>{

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public HCompany findOne(Long id) {

		HCompany hcompany = null;
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
	
		try (Connection connection = ds.getConnection();){
			// find hcompany
			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM HCOMPANY WHERE ID='" + id + "';";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if (rs.next()) {
				hcompany = new HCompany();
				hcompany.setId(rs.getLong("id"));
				hcompany.setName(rs.getString("name"));
			}
			
			// find hperson
			Statement stmt2 = connection.createStatement();
			String sqlCmd2 = "SELECT * FROM HPERSON WHERE COMPANYID='" + hcompany.getId() + "';";
			ResultSet rs2 = stmt2.executeQuery(sqlCmd2);
			
			Collection<HPerson> hpersons = new ArrayList<HPerson>();
			
			while(rs2.next()){
				HPerson hperson = new HPerson();
				hperson.setId(rs2.getLong("id"));
				hperson.setName(rs2.getString("name"));
				hperson.setIdNo(rs2.getString("idNo"));
				hperson.setCompanyid(rs2.getLong("companyid"));
				
				// find color
				Statement stmt3 = connection.createStatement();
				String sqlCmd3 = "SELECT * FROM COLOR WHERE PID='" + hperson.getId() + "';";
				ResultSet rs3 = stmt3.executeQuery(sqlCmd3);
				
				Collection<Color> colors = new ArrayList<Color>();
				
				while(rs3.next()){
					Color color = new Color();
					color.setId(rs3.getLong("id"));
					color.setName(rs3.getString("name"));
					color.setCode(rs3.getString("code"));
					color.setPid(rs3.getLong("pid"));
					colors.add(color);
				}
				hperson.setColors(colors);
				
				hpersons.add(hperson); 
				
				rs3.close();
				stmt3.close();
			}
			hcompany.setHpersons(hpersons);
			
			rs2.close();
			stmt2.close();
					
			rs.close();
			stmt.close();
			
			log.info("find one : id =  " + id + " => " + hcompany);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hcompany;
	}

	@Override
	public Collection<HCompany> findAll() {
		Collection<HCompany> hcompanys = new ArrayList<HCompany>();
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		
		try (Connection connection = ds.getConnection();){
			
			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT * FROM HCOMPANY;";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				HCompany hcompany = new HCompany();
				hcompany.setId(rs.getLong("id"));
				hcompany.setName(rs.getString("name"));
				
				// find hperson
				Statement stmt2 = connection.createStatement();
				String sqlCmd2 = "SELECT * FROM HPERSON WHERE COMPANYID='" + hcompany.getId() + "';";
				ResultSet rs2 = stmt2.executeQuery(sqlCmd2);
				
				Collection<HPerson> hpersons = new ArrayList<HPerson>();
				
				while(rs2.next()){
					HPerson hperson = new HPerson();
					hperson.setId(rs2.getLong("id"));
					hperson.setName(rs2.getString("name"));
					hperson.setIdNo(rs2.getString("idNo"));
					hperson.setCompanyid(rs2.getLong("companyid"));
					
					// find color
					Statement stmt3 = connection.createStatement();
					String sqlCmd3 = "SELECT * FROM COLOR WHERE PID='" + hperson.getId() + "';";
					ResultSet rs3 = stmt3.executeQuery(sqlCmd3);
					
					Collection<Color> colors = new ArrayList<Color>();
					
					while (rs3.next()) {
						Color color = new Color();
						color.setId(rs3.getLong("id"));
						color.setName(rs3.getString("name"));
						color.setCode(rs3.getString("code"));
						color.setPid(rs3.getLong("pid"));
						colors.add(color);
					}
					hperson.setColors(colors);

					hpersons.add(hperson);
					hcompany.setHpersons(hpersons);
					
					rs3.close();
					stmt3.close();
				}
				hcompanys.add(hcompany);
				
				rs2.close();
				stmt2.close();
			}
			rs.close();
			stmt.close();
			
			log.debug("find all =>  "  + hcompanys);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hcompanys;
	}

	@Override
	public void insert(HCompany entity) {
		
		log.info("insert start : ");
		
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		
		try (Connection connection = ds.getConnection();){
			// Company
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO HCOMPANY(NAME) VALUES ('" + entity.getName() + "');";
			log.debug("insert sql(HCompany) : " + sqlCmd);

			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			
			if(keySet.next()) {
				Long genCompanyId = keySet.getLong("ID");
				entity.setId(genCompanyId);
				log.debug("genCompanyId: " + genCompanyId);
			}
			
			// Person
			Iterator<HPerson> hpersonInsert = entity.getHpersons().iterator();
			
			while(hpersonInsert.hasNext()){
				Statement stmt2 = connection.createStatement();
				HPerson person = new HPerson();
				person = hpersonInsert.next();
				String sqlCmd2 = "INSERT INTO HPERSON(NAME,IDNO,COMPANYID) VALUES ('" + person.getName() + "', '" + person.getIdNo() +  "', "+ entity.getId() + ");";
				log.debug("insert sql(HPerson) : " + sqlCmd2);
				
				stmt2.execute(sqlCmd2, Statement.RETURN_GENERATED_KEYS);

				ResultSet keySetPerson = stmt2.getGeneratedKeys();
				
				if(keySetPerson.next()) {
					Long genPersonId = keySetPerson.getLong("ID");
					person.setId(genPersonId);
				}
				
				// Color
				Iterator<Color> colorInsert = person.getColors().iterator();
				
				while(colorInsert.hasNext()){
					Statement stmt3 = connection.createStatement();
					Color color = new Color();
					color = colorInsert.next();
					String sqlCmd3 = "INSERT INTO COLOR(NAME,CODE,PID) VALUES ('" + color.getName() + "', '" + color.getCode()+  "', "+ person.getId() + ");";
					log.debug("insert sql(Color) : " + sqlCmd3);
					stmt3.executeUpdate(sqlCmd3);
					stmt3.close();
				}
				
				stmt2.close();
			}
			
			stmt.close();
			log.info("insert end ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(HCompany entity) {
		
		log.info("update start : ");
		
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try (Connection connection = ds.getConnection();){
			
			Statement stmt = connection.createStatement();
			String sqlCmdHCompany = "UPDATE HCOMPANY SET NAME='" +  entity.getName() + "' WHERE ID='" + entity.getId() + "';";
			stmt.executeUpdate(sqlCmdHCompany);
			
			Statement stmt2 = connection.createStatement();
			String sqlCmd  = "SELECT * FROM HPERSON WHERE COMPANYID='" + entity.getId() + "';";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while(rs.next()){
				Statement stmtDelete = connection.createStatement();
				Long deletPId = rs.getLong("id");
				String sqlDelete  = "DELETE FROM COLOR WHERE PID='" + deletPId + "';";
				stmtDelete.executeUpdate(sqlDelete);
				stmtDelete.close();
			}
			
			Statement stmt3 = connection.createStatement();
			String sqlCmdHPersonDelete = "DELETE FROM HPERSON WHERE COMPANYID='" + entity.getId() + "';";
			stmt3.executeUpdate(sqlCmdHPersonDelete);
			
			Iterator<HPerson> hpersonUpdate = entity.getHpersons().iterator();
			while(hpersonUpdate.hasNext()){
				HPerson hperson = new HPerson();
				hperson = hpersonUpdate.next();
				Statement stmtHpersonUpdate = connection.createStatement();
				String sqlCmdHpersonUpdate = "INSERT INTO HPERSON(NAME,IDNO,COMPANYID) VALUES ('" + hperson.getName() + "', '" + hperson.getIdNo() +  "', "+ entity.getId() + ");";
				log.debug("insert sql(Hperson) : " + sqlCmdHpersonUpdate);
				stmtHpersonUpdate.execute(sqlCmdHpersonUpdate, Statement.RETURN_GENERATED_KEYS);
				
				ResultSet keySet = stmtHpersonUpdate.getGeneratedKeys();
				
				if(keySet.next()) {
					Long genHpersonId = keySet.getLong("ID");
					hperson.setId(genHpersonId);
					log.debug("genHpersonId: " + genHpersonId);
				}
				
				// insert color
				Iterator<Color> colorUpdate = hperson.getColors().iterator();
				while(colorUpdate.hasNext()){
					Color color = new Color();
					color = colorUpdate.next();
					Statement stmtColorUpdate = connection.createStatement();
					String sqlcmdColorUpdate = "INSERT INTO COLOR(NAME,CODE,PID) VALUES ('" + color.getName() + "', '" + color.getCode() +  "', "+ hperson.getId() + ");";
					stmtColorUpdate.executeUpdate(sqlcmdColorUpdate);
					stmtColorUpdate.close();
				}
				
				stmtHpersonUpdate.close();
			}

			
			stmt3.close();
			stmt2.close();
			stmt.close();
			log.info("update end ");
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		log.info("delete start : ");
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try (Connection connection = ds.getConnection();) {
			
			Statement stmt = connection.createStatement();
			String sqlCmd  = "SELECT * FROM HPERSON WHERE COMPANYID='" + id + "';";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			while(rs.next()){
				Statement stmtDelete = connection.createStatement();
				String sqlDelete  = "DELETE FROM COLOR WHERE PID='" + rs.getLong("id") + "';";
				log.debug("delete sql(Color) : " + sqlDelete);
				stmtDelete.executeUpdate(sqlDelete);
				stmtDelete.close();
			}
					
			Statement stmt2 = connection.createStatement();
			String sqlCmd2  = "DELETE FROM HPERSON WHERE COMPANYID='" + id + "';";
			log.debug("delete sql(Hperson) : " + sqlCmd2);
			stmt2.executeUpdate(sqlCmd2);
			
			Statement stmt3 = connection.createStatement();
			String sqlCmd3  = "DELETE FROM HCOMPANY WHERE ID='" + id + "';";
			log.debug("delete sql(HCOMPANY) : " + sqlCmd3);
			stmt3.executeUpdate(sqlCmd3);
			
			rs.close();
			stmt.close();
			
			stmt3.close();
			stmt2.close();
			stmt.close();
			log.info("delete end ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
