package tw.com.softleader.sample.notebook;

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

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

/**
 * <br> 筆記型電腦
 * @author Frank
 *
 */
public class NotebookDao implements GenericDao<Notebook> {
	
	private Logger log = LoggerFactory.getLogger(NotebookDao.class);

	@Override
	public Notebook findOne(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Notebook entity = null;
		try {
			Connection connection = ds.getConnection();
			Statement statement = connection.createStatement();
			String sql = "select * from notebook where id = " + id;
			ResultSet rs = statement.executeQuery(sql);
			log.info("sql = "+sql);
			log.debug("id = " + id);
			if(rs.next()) {
				entity = new Notebook();
				entity.setId(rs.getLong("id"));
				entity.setBrand(rs.getString("brand"));
				entity.setSpecification(rs.getString("specification"));
				entity.setQuotation(rs.getBigDecimal("quotation"));
				log.debug("entity = " + entity.toString());
			}
			rs.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public Collection<Notebook> findByWpersonId(Long wpersonId) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Collection<Notebook> notebooks = new ArrayList<Notebook>();
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = "select * from notebook where wperson_id = " + wpersonId;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				Notebook notebook = new Notebook();
				notebook.setId(rs.getLong("id"));
				notebook.setBrand(rs.getString("brand"));
				notebook.setSpecification(rs.getString("specification"));
				notebook.setQuotation(rs.getBigDecimal("quotation"));
				notebook.setWpersonId(rs.getLong("wperson_id"));
				notebooks.add(notebook);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notebooks;
	}

	@Override
	public Collection<Notebook> findAll() {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		List<Notebook> notebooks = new ArrayList<Notebook>();
		try {
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			String sql = "select * from notebook";
			log.info("sql = "+sql);
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Notebook entity = new Notebook();
				entity.setId(rs.getLong("id"));
				entity.setBrand(rs.getString("brand"));
				entity.setSpecification(rs.getString("specification"));
				entity.setQuotation(rs.getBigDecimal("quotation"));
				log.debug("entity = " + entity.toString());
				notebooks.add(entity);
			}
			rs.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notebooks;
	}

	@Override
	public void insert(Notebook entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			String sql = "insert into notebook (brand, specification, quotation) values ('" + entity.getBrand() + "', '" + entity.getSpecification() + "', " + entity.getQuotation() + ");";
			log.info("sql = " + sql);
			statement.execute(sql, Statement.RETURN_GENERATED_KEYS);		// 代表PK會自動產生
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				entity.setId(rs.getLong("id"));
			}
			rs.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Notebook entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			String sql = " update notebook set brand = '" + entity.getBrand() + "', " +
						 " specification = '" + entity.getSpecification() + "', " +
						 " quotation = " + entity.getQuotation() + ", " +
						 " wperson_id = " + entity.getWpersonId() +
						 " where id = " + entity.getId();
			log.info("sql = " + sql);
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			String sql = "delete from notebook where id = " + id;
			log.info("sql = " + sql);
			statement.executeUpdate(sql);
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
