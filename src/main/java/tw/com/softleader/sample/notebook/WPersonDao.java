package tw.com.softleader.sample.notebook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

public class WPersonDao implements GenericDao<WPerson> {

	private Logger log = LoggerFactory.getLogger(WPersonDao.class);
		
	@Override
	public WPerson findOne(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		WPerson entity = null;
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = " select * from wperson where id = " + id;
			log.info("sql = " + sql);
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				entity = new WPerson();
				entity.setId(rs.getLong("id"));
				entity.setIdno(rs.getString("idno"));
				entity.setName(rs.getString("name"));
				entity.setNotebooks(new ArrayList<Notebook>());
				Statement stat2 = conn.createStatement();
				String sqlDtl = " select * from notebook where wperson_id = " + id;
				log.info("sqlDtl" + sqlDtl);
				ResultSet rsDtl = stat2.executeQuery(sqlDtl);
				while(rsDtl.next()) {
					Notebook notebook = new Notebook();
					notebook.setId(rsDtl.getLong("id"));
					notebook.setBrand(rsDtl.getString("brand"));
					notebook.setSpecification(rsDtl.getString("specification"));
					notebook.setQuotation(rsDtl.getBigDecimal("quotation"));
					notebook.setWpersonId(entity.getId());
					entity.getNotebooks().add(notebook);
				}
				rsDtl.close();
				stat2.close();
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public Collection<WPerson> findAll() {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		Collection<WPerson> wPersons = new ArrayList<WPerson>();
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = " select * from wperson ";
			log.info("sql = " + sql);
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				WPerson entity = new WPerson();
				entity.setId(rs.getLong("id"));
				entity.setIdno(rs.getString("idno"));
				entity.setName(rs.getString("name"));
				entity.setNotebooks(new ArrayList<Notebook>());
				// 在執行另一個query時，原來的ResultSet會被關閉，需新增一個statement
				Statement stat2 = conn.createStatement();
				String sqlDtl = " select * from notebook where wperson_id = " + entity.getId();
				ResultSet rsDtl = stat2.executeQuery(sqlDtl);
				if(rsDtl.next()) {
					Notebook notebook = new Notebook();
					notebook.setId(rsDtl.getLong("id"));
					notebook.setBrand(rsDtl.getString("brand"));
					notebook.setSpecification(rsDtl.getString("specification"));
					notebook.setQuotation(rsDtl.getBigDecimal("quotation"));
					notebook.setWpersonId(entity.getId());
					entity.getNotebooks().add(notebook);
				}
				wPersons.add(entity);
				rsDtl.close();
				stat2.close();
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wPersons;
	}

	@Override
	public void insert(WPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = " insert into wperson (idno, name) values ('" + entity.getIdno() + "', '" + entity.getName() + "');";
			log.info("sql = " + sql);
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stat.getGeneratedKeys();
			if(rs.next()) {
				entity.setId(rs.getLong("id"));
			}
			if(entity.getNotebooks() != null) {
				NotebookDao notebookDao = new NotebookDao();
				for(Notebook notebook : entity.getNotebooks()) {
					notebookDao.insert(notebook);
					notebook.setWpersonId(entity.getId());
					notebookDao.update(notebook);
				}
			}
			rs.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(WPerson entity) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = " update wperson set " +
						 " idno = '" + entity.getIdno() + "', " +
						 " name = '" + entity.getName() + "' " +
						 " where id = " + entity.getId();
			log.info("sql = " + sql);
			stat.executeUpdate(sql);
			Map<Long, Notebook> dbNotebookMap = new HashMap<Long, Notebook>();
			NotebookDao notebookDao = new NotebookDao();
			Collection<Notebook> dbNotebooks = notebookDao.findByWpersonId(entity.getId()); 
			for(Notebook dbNotebook : dbNotebooks) {
				dbNotebookMap.put(dbNotebook.getId(), dbNotebook);
			}

			Map<Long, Notebook> entityNotebooksMap = new HashMap<Long, Notebook>();
			if(entity.getNotebooks() != null && !entity.getNotebooks().isEmpty()) {
				entity.getNotebooks().stream().forEach(dbNotebook -> {
					if(dbNotebookMap.get(dbNotebook.getId()) == null) {
						notebookDao.insert(dbNotebook);
					} else {
						notebookDao.update(dbNotebook);
					}
					entityNotebooksMap.put(dbNotebook.getId(), dbNotebook);
				});
				dbNotebooks.stream()
							.filter(dbNotebook -> entityNotebooksMap.get(dbNotebook.getId()) == null)
							.forEach(dbNotebook -> notebookDao.delete(dbNotebook.getId()));
			} else {
				dbNotebooks.stream().forEach(dbNotebook -> notebookDao.delete(dbNotebook.getId()));
			}
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		DataSource ds = DataSourceUtil.getInstance().getDataSource();
		try {
			Connection conn = ds.getConnection();
			Statement stat = conn.createStatement();
			String sql = "delete from wperson where id = " + id;
			log.info("sql = " + sql);
			stat.executeUpdate(sql);
			Statement stat2 = conn.createStatement();
			sql = "delete from notebook where wperson_id = " + id;
			stat2.executeUpdate(sql);
			stat2.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
