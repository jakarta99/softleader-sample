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

			String sqlCmd = "select id,name,idnum from sperson where id = ?";
			String sqlCmdsport = "select id,name,people,personid from sport where personid = ?";
			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			pstmt.setLong(1, id);

			ResultSet rs = pstmt.executeQuery();
			ResultSet keySet = pstmt.getGeneratedKeys();

			if (rs.next()) {
				sperson.setId(rs.getLong("id"));
				sperson.setName(rs.getString("name"));
				sperson.setIdnum(rs.getString("idnum"));

				sport.add(sportdao.findOne(rs.getLong("id")));
				sperson.setSports(sport);
			}

			if (keySet.next()) {
				Long generatedId = keySet.getLong("id");
				pstmt = connection.prepareStatement(sqlCmdsport);
				pstmt.setLong(1, generatedId);
				ResultSet rssport = pstmt.executeQuery();
				Sport temp = new Sport();

				temp.setId(rssport.getLong("id"));
				temp.setName(rssport.getString("name"));
				temp.setPeople(rssport.getString("people"));

				Collection<Sport> tempsports = new ArrayList<Sport>();
				tempsports.add(temp);
				sperson.setSports(tempsports);
			}
			rs.close();
			keySet.close();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sperson;
	}

	@Override
	public Collection<SPerson> findAll() {

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		Collection<SPerson> sperson = new ArrayList<SPerson>();

		Collection<Sport> tempsports = new ArrayList<Sport>();

		String sqlCmd = "select * from SPerson ;";
		String sqlCmdsport = "select * from sport where personid = ?";

		try {

			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = pstmt.executeQuery();
			ResultSet keySet = pstmt.getGeneratedKeys();

			while (rs.next()) {
				SPerson temp = new SPerson();
				temp.setId(rs.getLong("id"));
				temp.setName(rs.getString("name"));
				temp.setIdnum(rs.getString("idnum"));

				pstmt = connection.prepareStatement(sqlCmdsport);
				
				while(keySet.next()){
										
				pstmt.setLong(1, keySet.getLong("id"));
				ResultSet rs1 = pstmt.executeQuery();

				while (rs1.next()) {

					Sport psport = new Sport();

					psport.setId(rs.getLong("id"));
					psport.setName(rs.getString("name"));
					psport.setPeople(rs.getString("people"));

					tempsports.add(psport);

					SPerson tempspersons = new SPerson();
					tempspersons.setSports(tempsports);
					sperson.add(tempspersons);
					rs1.close();
					}
				}
			}

			rs.close();
			keySet.close();
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

		String sqlCmd = "INSERT INTO SPerson(name,idnum) VALUES (?,?);";
		String sqlCmdsport = "INSERT INTO sport(name,people,psrsonid) VALUES (?,?,?);";
		
		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getIdnum());			

			pstmt.executeUpdate();

			ResultSet keySet = pstmt.getGeneratedKeys();

			while (keySet.next()) {
				while(entity.getSports().iterator().hasNext()){
				Long generatedId = keySet.getLong("id");
				pstmt = connection.prepareStatement(sqlCmdsport);
				
				pstmt.setString(1, entity.getSports().iterator().next().getName());
				pstmt.setString(2, entity.getSports().iterator().next().getPeople());		
				pstmt.setLong(3, generatedId);
				pstmt.executeUpdate();
				}
				
//				entity.setId(generatedId);
			}
			
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

		String sqlCmd = "UPDATE SPerson SET name=?, idnum=? WHERE id=? ;";  
		String sqlCmddelsport = "DELETE FROM sport WHERE personid=? ;";  
		String sqlCmdinssport = "INSERT INTO sport(name,people,psrsonid) VALUES (?,?,?);";   



		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmd);
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getIdnum());
			pstmt.setLong(3, entity.getId());

			pstmt.executeUpdate();
			
			pstmt = connection.prepareStatement(sqlCmddelsport);
			pstmt.setLong(1, entity.getId());

			pstmt.executeUpdate();
			
			while(entity.getSports().iterator().hasNext()){
				
			pstmt = connection.prepareStatement(sqlCmdinssport);
			pstmt.setString(1, entity.getSports().iterator().next().getName());
			pstmt.setString(2, entity.getSports().iterator().next().getPeople());
			pstmt.setLong(1, entity.getId());
			
			}
			
			
			pstmt.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Long id) {

		String sqlCmddelsport = "DELETE FROM sport WHERE personid=? ;";  
		String sqlCmd = "delete from SPerson where id=?";

		DataSource datasource = DataSourceUtil.getInstance().getDataSource();

		try {
			Connection connection = datasource.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(sqlCmddelsport);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();

			pstmt = connection.prepareStatement(sqlCmd);
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
