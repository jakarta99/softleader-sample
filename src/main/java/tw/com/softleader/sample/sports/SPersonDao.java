package tw.com.softleader.sample.sports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class SPersonDao implements GenericDao<SPerson> {
	private Logger log = Logger.getLogger(SPersonDao.class);
	private SportDao sportdao = new SportDao();

	@Override
	public SPerson findOne(Long id) {
		SPerson sperson = new SPerson();

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		Collection<Sport> sport = new ArrayList<Sport>();
		try {

			Connection connection = datasource.getConnection();

			String sqlCmd = "select p.id,p.name,p.idnum,p.sportid,s.name,s.people from SPerson p join sport s on p.sportid=s.id where p.id=? ;";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				sperson.setId(rs.getLong("id"));
				sperson.setName(rs.getString("name"));
				sperson.setIdnum(rs.getString("idnum"));

				sport.add(sportdao.findOne(rs.getLong("id")));
				sperson.setSports(sport);

			} else {
				return sperson;
			}

			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Collection<SPerson> findAll() {

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		Collection<SPerson> sperson = new ArrayList<SPerson>();

		Collection<Sport> sport = new ArrayList<Sport>();
		try {

			Connection connection = datasource.getConnection();

			String sqlCmd = "select * from SPerson p join sport s on p.sportid=s.id;";

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				SPerson temp = new SPerson();
				temp.setId(rs.getLong("id"));
				temp.setName(rs.getString("name"));
				temp.setIdnum(rs.getString("idnum"));

				sport.add(sportdao.findOne(rs.getLong("id")));
				temp.setSports(sport);

				sperson.add(temp);
			}

			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sperson;
	}

	@Override
	public void insert(SPerson entity) {

		Long tempsportid = entity.getSports().iterator().next().getId();

		String sqlCmd = "INSERT INTO SPerson(name,idnum,sportid) VALUES (?,?,?);";

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getIdnum());
			pstmt.setLong(3, tempsportid);

			pstmt.executeUpdate();

			ResultSet keySet = pstmt.getGeneratedKeys();
			
			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				entity.setId(generatedId);
			}
			log.debug("2: insertDao -->"+keySet.getLong(1) );
			keySet.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(SPerson entity) {

		Long tempsportid = entity.getSports().iterator().next().getId();

		String sqlCmd = "UPDATE SPerson SET sportid=? WHERE id=? ";

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, tempsportid);
			pstmt.setLong(2, entity.getId());

			pstmt.executeUpdate();

			pstmt.close();
			connection.close();
log.debug("Dao tempsportid-->"+tempsportid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {

		String sqlCmd = "delete from SPerson where id=?";
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
