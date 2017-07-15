package tw.com.softleader.sample.sports;

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

public class SComDao implements GenericDao<SCom> {

	private Logger log = Logger.getLogger(this.getClass());
	Collection<Sport> sportlist = null; // new ArrayList<Sport>();
	Collection<SPerson> spersonlist = null; // new ArrayList<SPerson>();
	Collection<SCom> scomlist = null; // new ArrayList<SCom>();
	SCom scom = null;
	SPerson sperson = null;
	Sport sport = null;

	@Override
	public SCom findOne(Long id) {

		String sqlCmdcom = "select id,name from SCom where id = ?";
		String sqlCmdperson = "select id,name,idnum,comid from SPerson where comid = ?";
		String sqlCmdsport = "select id,name,people,personid from sport where personid = ?";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {

			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmdcom);

			pstmt.setLong(1, id);

			ResultSet rsCom = pstmt.executeQuery();

			if (rsCom.next()) {
				scom = new SCom();
				scom.setId(rsCom.getLong("id"));
				scom.setName(rsCom.getString("name"));
				spersonlist = new ArrayList<SPerson>();

				log.debug("DAO: findOne SComName = " + rsCom.getString("NAME"));

				PreparedStatement pstmt2 = connection.prepareStatement(sqlCmdperson);
				pstmt2.setLong(1, id);
				ResultSet rsPerson = pstmt2.executeQuery();
				while (rsPerson.next()) {
					SPerson sperson = new SPerson();
					sperson.setComid(rsCom.getLong("id"));
					sperson.setId(rsPerson.getLong("id"));
					sperson.setIdnum(rsPerson.getString("idnum"));
					sperson.setName(rsPerson.getString("name"));
					sportlist = new ArrayList<Sport>();

					log.debug("DAO: findOne personName = " + rsPerson.getString("name"));

					PreparedStatement pstmt3 = connection.prepareStatement(sqlCmdsport);
					pstmt3.setLong(1, rsPerson.getLong("id"));
					ResultSet rsSport = pstmt3.executeQuery();

					while (rsSport.next()) {
						sport = new Sport();
						sport.setPersonid(rsPerson.getLong("id"));
						sport.setId(rsSport.getLong("id"));
						sport.setName(rsSport.getString("name"));
						sport.setPeople(rsSport.getString("people"));

						log.debug("DAO: findOne sportName = " + rsSport.getString("name"));

						sportlist.add(sport);

					}
					rsSport.close();

					sperson.setSports(sportlist);

					spersonlist.add(sperson);
				}
				rsPerson.close();
				scom.setSperson(spersonlist);
			}

			rsCom.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return scom;
	}

	@Override
	public Collection<SCom> findAll() {

		String sqlCmdcom = "select id from SCom ";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();
		scomlist = new ArrayList<SCom>();
		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmdcom);

			ResultSet rsCom = pstmt.executeQuery();

			while (rsCom.next()) {
				SCom tempscom = SComDao.this.findOne(rsCom.getLong("id"));
				scomlist.add(tempscom);
			}

			rsCom.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return scomlist;
	}

	@Override
	public void insert(SCom entity) {
		String sqlCmdcom = "INSERT INTO SCom (name) VALUES (?) ";
		String sqlCmdperson = "INSERT INTO SPerson (name,idnum,comid) VALUES (?,?,?) ";
		String sqlCmdsport = "INSERT INTO sport (name,people,personid) VALUES (?,?,?) ";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();
		Long comid = null;
		Long personid = null;
		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlCmdcom, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getName());
			pstmt.executeUpdate();
			ResultSet keySetCom = pstmt.getGeneratedKeys();

			if (keySetCom.next()) {
				comid = keySetCom.getLong("id");
				entity.setId(comid);
			}

			Iterator<SPerson> spersonindex = entity.getSperson().iterator();
			while (spersonindex.hasNext()) {
				SPerson insertSperson = spersonindex.next();
				pstmt = connection.prepareStatement(sqlCmdperson, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, insertSperson.getName());
				pstmt.setString(2, insertSperson.getIdnum());
				pstmt.setLong(3, comid);
				pstmt.executeUpdate();
				ResultSet keySetPerson = pstmt.getGeneratedKeys();

				// entity.getSperson().iterator().next().setId(keySetPerson.getLong("id"));
				// entity.getSperson().iterator().next().setComid(keySetCom.getLong("id"));

				if (keySetPerson.next()) {
					personid = keySetPerson.getLong("id");
					insertSperson.setId(personid);
					insertSperson.setComid(keySetCom.getLong("id"));
				}

				Iterator<Sport> sportindex = insertSperson.getSports().iterator();

				while (sportindex.hasNext()) {
					Sport insertSport = sportindex.next();

					pstmt = connection.prepareStatement(sqlCmdsport, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, insertSport.getName());
					pstmt.setString(2, insertSport.getPeople());
					pstmt.setLong(3, personid);
					pstmt.executeUpdate();

					ResultSet keySetSport = pstmt.getGeneratedKeys();
					Long sport_Id = null;
					if (keySetSport.next()) {
						sport_Id = keySetSport.getLong("id");
						insertSport.setId(sport_Id);
						insertSport.setPersonid(personid);
						// entity.getSperson().iterator().next().getSports().iterator().next().setId(keySetSport.getLong("id"));
						// entity.getSperson().iterator().next().getSports().iterator().next().setPersonid(keySetPerson.getLong("id"));
					}

					keySetSport.close();
				}

				keySetPerson.close();
			}

			keySetCom.close();
			pstmt.close();
			connection.close();

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(SCom entity) {

		SComDao.this.delete(entity.getId());

		SComDao.this.insert(entity);

	}

	@Override
	public void delete(Long id) {
		String sqlCmdcom = " DELETE from SCom WHERE id = ?";
		String sqlCmdperson = "DELETE from SPerson WHERE comid = ?";
		String sqlCmdsport = "DELETE from sport WHERE personid = ?";
		boolean persondeleteflag = false;
		boolean sportdeleteflag = false;

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {

			Connection connection = datasource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlCmdsport);

			Iterator<SPerson> personDeleteIndex = SComDao.this.findOne(id).getSperson().iterator();

			while (personDeleteIndex.hasNext()) {
				Long delId = personDeleteIndex.next().getSports().iterator().next().getPersonid();
				log.debug("delete sport id (person id) = " + delId);
//				Iterator<Sport> sportDeleteFlag = personDeleteIndex.next().getSports().iterator();
				pstmt.setLong(1, delId);
				pstmt.executeUpdate();
				sportdeleteflag = true;
				//				log.debug("delete sport = "+sportDeleteFlag.next().getName());
//				if (sportDeleteFlag.hasNext()) {
//				}
			}
			if (sportdeleteflag) {
				pstmt = connection.prepareStatement(sqlCmdperson);

				pstmt.setLong(1, id);
				pstmt.executeUpdate();
				log.debug("delete person id  = " + id);
				
				persondeleteflag = true;
				if (persondeleteflag) {
					pstmt = connection.prepareStatement(sqlCmdcom);

					pstmt.setLong(1, id);
					pstmt.executeUpdate();
					log.debug("delete com id  = " + id);

				}
			}

			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
