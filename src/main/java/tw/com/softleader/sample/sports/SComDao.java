package tw.com.softleader.sample.sports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.sql.DataSource;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class SComDao implements GenericDao<SCom> {

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

				pstmt = connection.prepareStatement(sqlCmdperson);
				pstmt.setLong(1, id);
				ResultSet rsPerson = pstmt.executeQuery();
				while (rsPerson.next()) {
					sperson = new SPerson();
					sperson.setComid(rsCom.getLong("id"));
					sperson.setId(rsPerson.getLong("id"));
					sperson.setIdnum(rsPerson.getString("idnum"));
					sperson.setName(rsPerson.getString("name"));

					pstmt = connection.prepareStatement(sqlCmdsport);
					pstmt.setLong(1, rsPerson.getLong("id"));
					ResultSet rsSport = pstmt.executeQuery();

					while (rsSport.next()) {
						sport = new Sport();
						sport.setPersonid(rsPerson.getLong("id"));
						sport.setId(rsSport.getLong("id"));
						sport.setName(rsSport.getString("name"));
						sport.setPeople(rsSport.getString("people"));

						sportlist = new ArrayList<Sport>();
						spersonlist = new ArrayList<SPerson>();

						sportlist.add(sport);
						sperson.setSports(sportlist);
						spersonlist.add(sperson);
						scom.setSperson(spersonlist);

						rsSport.close();
						rsPerson.close();

					}
				}
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

		String sqlCmdcom = "select * from SCom ";
		String sqlCmdperson = "select * from SPerson ";
		String sqlCmdsport = "select * from sport ";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {

			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmdcom);

			ResultSet rsCom = pstmt.executeQuery();

			if (rsCom.next()) {
				scom = new SCom();
				scom.setId(rsCom.getLong("id"));
				scom.setName(rsCom.getString("name"));

				pstmt = connection.prepareStatement(sqlCmdperson);

				ResultSet rsPerson = pstmt.executeQuery();
				while (rsPerson.next()) {
					sperson = new SPerson();
					sperson.setComid(rsCom.getLong("id"));
					sperson.setId(rsPerson.getLong("id"));
					sperson.setIdnum(rsPerson.getString("idnum"));
					sperson.setName(rsPerson.getString("name"));

					pstmt = connection.prepareStatement(sqlCmdsport);

					ResultSet rsSport = pstmt.executeQuery();

					while (rsSport.next()) {
						sport = new Sport();
						sport.setPersonid(rsPerson.getLong("id"));
						sport.setId(rsSport.getLong("id"));
						sport.setName(rsSport.getString("name"));
						sport.setPeople(rsSport.getString("people"));

						sportlist = new ArrayList<Sport>();
						spersonlist = new ArrayList<SPerson>();
						scomlist = new ArrayList<SCom>();

						sportlist.add(sport);
						sperson.setSports(sportlist);
						spersonlist.add(sperson);
						scom.setSperson(spersonlist);
						scomlist.add(scom);

						rsSport.close();
						rsPerson.close();

					}
				}
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
		String sqlCmdsport = "INSERT INTO SPerson (name,people,personid) VALUES (?,?,?) ";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {

			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmdcom, pstmt.RETURN_GENERATED_KEYS);

			pstmt.setString(1, entity.getName());

			pstmt.executeUpdate();
			ResultSet keySetCom = pstmt.getGeneratedKeys();

			Iterator<SPerson> spersonindex = entity.getSperson().iterator();
			while (spersonindex.hasNext()) {
				sperson = spersonindex.next();
				pstmt = connection.prepareStatement(sqlCmdperson, pstmt.RETURN_GENERATED_KEYS);

				pstmt.setString(1, sperson.getName());
				pstmt.setString(2, sperson.getIdnum());
				pstmt.setLong(3, keySetCom.getLong("id"));

				pstmt.executeUpdate();
				ResultSet keySetPerson = pstmt.getGeneratedKeys();

				sperson.setId(keySetPerson.getLong("id"));
				sperson.setComid(keySetCom.getLong("id"));

				if (keySetPerson.next()) {

					Iterator<Sport> sportindex = sperson.getSports().iterator();

					while (sportindex.hasNext()) {
						sport = sportindex.next();

						pstmt = connection.prepareStatement(sqlCmdsport, pstmt.RETURN_GENERATED_KEYS);
						pstmt.setString(1, sport.getName());
						pstmt.setString(2, sport.getPeople());
						pstmt.setLong(3, keySetPerson.getLong("id"));
						pstmt.executeUpdate();

						ResultSet keySetSport = pstmt.getGeneratedKeys();

						sport.setId(keySetSport.getLong("id"));
						sport.setPersonid(keySetPerson.getLong("id"));
						keySetSport.close();
						keySetPerson.close();

					}
				}
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
		String sqlCmdsearch = " SELECT id from SCom WHERE personid = ?";
		boolean persondeleteflag = false;
		boolean sportdeleteflag = false;

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {

			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmdsearch);
			pstmt.setLong(1, id);

			ResultSet rssearch = pstmt.executeQuery();
			if (rssearch.next()) {

				pstmt = connection.prepareStatement(sqlCmdsport);

				pstmt.setLong(1, rssearch.getLong("id"));
				pstmt.executeUpdate();
				sportdeleteflag = true;

				if (sportdeleteflag) {
					pstmt = connection.prepareStatement(sqlCmdperson);

					pstmt.setLong(1, id);
					pstmt.executeUpdate();
					persondeleteflag = true;
					if (sportdeleteflag) {
						pstmt = connection.prepareStatement(sqlCmdcom);

						pstmt.setLong(1, id);
						pstmt.executeUpdate();

					}
				}

			}

			rssearch.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
