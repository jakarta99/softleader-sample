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

public class KPersonDao implements GenericDao<KPerson> {
	
	
	private Logger log = LoggerFactory.getLogger(KPersonDao.class);

	
	@Override
	public KPerson findOne(Long id) {
		KPerson entity = null;
		List<Cloth>clothes = new ArrayList<>();
		
		try {
			
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM K_PERSON WHERE PERSON_ID = " + id;
			
			log.debug("1:"+sqlCmd);
			log.info("2:"+sqlCmd);
			log.warn("3:"+sqlCmd);
			log.error("4:"+sqlCmd);
			
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			if(rs.next()) {
				
				entity = new KPerson();
				entity.setId(rs.getLong("person_id"));
				entity.setName(rs.getString("person_name"));
				entity.setIdNo(rs.getString("id_No"));
			}
			
			sqlCmd = "SELECT * FROM CLOTH WHERE OWNER_ID = " + id;
			 rs = stmt.executeQuery(sqlCmd);
			 Cloth cloth = null;
			 while(rs.next()){
				 cloth = new Cloth();
				 cloth.setId(rs.getLong("cloth_id"));
				 cloth.setColor(rs.getString("color"));
				 cloth.setOwnerId(rs.getLong("owner_Id"));
				 cloth.setName(rs.getString("cloth_name"));
				 clothes.add(cloth);
				 
			 }
			 entity.setClothes(clothes);
			
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
	public Collection<KPerson> findAll() {
		
		Collection<KPerson> persons = new ArrayList<KPerson>();
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "SELECT * FROM K_PERSON";
			
			ResultSet rs = stmt.executeQuery(sqlCmd);
			
			while(rs.next()) {
				
				KPerson entity = new KPerson();
				entity.setId(rs.getLong("person_id"));
				entity.setName(rs.getString("person_name"));
				entity.setIdNo(rs.getString("id_No"));
				
				persons.add(entity);
				
			}
			for(KPerson person : persons){
				sqlCmd = "SELECT * FROM CLOTH where owner_id ="+person.getId();
				rs = stmt.executeQuery(sqlCmd);
				Collection<Cloth> clothes = new ArrayList<>();
				while(rs.next()) {
					
					Cloth entity = new Cloth();
					entity.setId(rs.getLong("cloth_id"));
					entity.setName(rs.getString("cloth_name"));
					entity.setColor(rs.getString("color"));
					entity.setOwnerId(person.getId());
					
					clothes.add(entity);
					
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
		
		
		return persons;
	}

	@Override
	public void insert(KPerson entity) {
		
		
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "INSERT INTO K_PERSON (person_name, id_no) VALUES ('"+entity.getName()+"','"+entity.getIdNo()+"');";
			
			stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet keySet = stmt.getGeneratedKeys();
			Long personGeneratedId = 0L; 
			if(keySet.next()) {
				personGeneratedId = keySet.getLong("PERSON_ID");
				entity.setId(personGeneratedId);
			}
			 Collection<Cloth> clothes = entity.getClothes();
			for (Cloth c : clothes) {
				sqlCmd = "INSERT INTO CLOTH (cloth_name, color,owner_id) VALUES ('" + c.getName() + "','" + c.getColor()+"','" + personGeneratedId
						+ "');";

				stmt.execute(sqlCmd, Statement.RETURN_GENERATED_KEYS);

				keySet = stmt.getGeneratedKeys();

				if (keySet.next()) {
					Long generatedId = keySet.getLong("CLOTH_ID");
					entity.setId(generatedId);
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
	public void update(KPerson entity) {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			
			String sqlCmd = "UPDATE K_PERSON SET "
								+ "person_name = '" + entity.getName() + "', "
								+ "id_no = '" + entity.getIdNo() + "'  "
								+ "WHERE PERSON_ID = " + entity.getId();
			
			stmt.executeUpdate(sqlCmd);
			if (entity.getClothes() != null && !entity.getClothes().isEmpty()) {
				Cloth cloth = entity.getClothes().stream().findAny().get();
				sqlCmd = "UPDATE CLOTH SET " + "cloth_name = '" + cloth.getName() + "', " + "color = '"
						+ cloth.getColor() + "'  " + "WHERE OWNER_ID = " + entity.getId();

				stmt.executeUpdate(sqlCmd);
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
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();
			
			Statement stmt = connection.createStatement();
			String sqlCmd = "DELETE FROM CLOTH WHERE OWNER_ID = " + id;

			stmt.executeUpdate(sqlCmd);
			
			 sqlCmd = "DELETE FROM K_PERSON WHERE PERSON_ID = "+id;
			
			stmt.executeUpdate(sqlCmd);
			
			
			stmt.close();
			
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
