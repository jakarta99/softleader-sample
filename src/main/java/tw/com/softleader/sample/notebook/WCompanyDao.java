package tw.com.softleader.sample.notebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.softleader.sample.commons.DataSourceUtil;
import tw.com.softleader.sample.commons.GenericDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Frank
 *
 */
public class WCompanyDao implements GenericDao<WCompany> {

	private Logger log = LoggerFactory.getLogger(WCompanyDao.class);
	
	private Connection conn = null;
	
	private Connection getConnection() throws SQLException{
		if(conn == null || conn.isClosed()) {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			conn = ds.getConnection();
		}
		return conn;
	}

	@Override
	public WCompany findOne(Long id) {
		log.info("findOne");
		WCompany entity = null;
		try {
			Statement stat = getConnection().createStatement();
			String sql = "select * from w_company where id = " + id;
			log.info(sql);
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				entity = resultSetToWCompany(rs);

				Statement personStat = getConnection().createStatement();
				String personSql = "select * from w_person where w_company_id = " + id;
				log.info(personSql);
				ResultSet personRs = personStat.executeQuery(personSql);
				while(personRs.next()) {
					WPerson person = resultSetToWPerson(personRs);
					entity.getPersonList().add(person);

					Statement notebookStat = getConnection().createStatement();
					String notebookSql = "select * from notebook where w_person_id = " + person.getId();
					log.info(notebookSql);
					ResultSet notebookRs = notebookStat.executeQuery(notebookSql);
					while (notebookRs.next()) {
						Notebook notebook = resultSetToNotebook(notebookRs);
						person.getNotebooks().add(notebook);
					}
					notebookRs.close();
					notebookStat.close();
				}
				personRs.close();
				personStat.close();
			}
			rs.close();
			stat.close();
			getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Collection<WCompany> findAll() {
		log.info("findAll");
		Collection<WCompany> companies = new ArrayList<WCompany>();
		try {
			String sql = "select * from w_company";
			Statement stat = getConnection().createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				WCompany company = resultSetToWCompany(rs);

				String personSql = "select * from w_person where w_company_id = " + company.getId();
				Statement personStat = getConnection().createStatement();
				ResultSet personRs = personStat.executeQuery(personSql);
				while(personRs.next()) {
					WPerson person = resultSetToWPerson(personRs);
					company.getPersonList().add(person);

					String nbSql = "select * from notebook where w_person_id = " + person.getId();
					Statement nbStat = getConnection().createStatement();
					ResultSet nbRs = nbStat.executeQuery(nbSql);
					while (nbRs.next()) {
						Notebook nb = resultSetToNotebook(nbRs);
						person.getNotebooks().add(nb);
					}
					nbRs.close();
					nbStat.close();
				}
				personRs.close();
				personStat.close();
			}
			rs.close();
			stat.close();
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return companies;
	}

	@Override
	public void insert(WCompany entity) {
		log.info("insert");
		try {
			String sql = "insert into w_company(idno, name) values('" + entity.getIdno() + "', '" + entity.getName() + "');";
			Statement stat = getConnection().createStatement();
			stat.execute(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stat.getGeneratedKeys();
			if(rs.next()) {
				entity.setId(rs.getLong("id"));
			}
			rs.close();
			stat.close();
			log.info("sql = " + sql);
			log.info("entity.getId() = " + entity.getId());
			if(entity.getPersonList() != null || !entity.getPersonList().isEmpty()) {
				for(WPerson wPerson : entity.getPersonList()) {
					wPerson.setCompanyId(entity.getId());
					insertWPerson(wPerson);
					if(wPerson.getNotebooks() != null || !wPerson.getNotebooks().isEmpty()) {
						for(Notebook nb : wPerson.getNotebooks()) {
							nb.setWpersonId(wPerson.getId());
							insertNotebook(nb);
						}
					}
				}
			}
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(WCompany entity) {
		log.info("update");
		try {
			// 先取得DB的公司資料
			WCompany dbCompany = findOne(entity.getId());

			String sql = " update w_company set idno = '" + entity.getIdno() + "'," +
						 " name = '" + entity.getName() + "' " +
						 " where id = " + entity.getId();
			log.info(sql);
			Statement stat = getConnection().createStatement();
			stat.executeUpdate(sql);

			saveWPersons(entity.getPersonList(), dbCompany.getPersonList());


			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		log.info("delete");
		try {
			WCompany dbCompany = findOne(id);
			if(dbCompany.getPersonList() != null) {
				for(WPerson dbPerson : dbCompany.getPersonList()) {
					if (dbPerson != null) {
						for (Notebook dbNb : dbPerson.getNotebooks()) {
							deleteNotebook(dbNb.getId());
						}
					}
					deleteWPerson(dbPerson.getId());
				}
			}
			String sql = "delete from w_company where id = " + id;
			Statement stat = getConnection().createStatement();
			stat.executeUpdate(sql);
			stat.close();
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private WCompany resultSetToWCompany(ResultSet rs) throws SQLException {
		WCompany entity = new WCompany();
		entity.setId(rs.getLong("id"));
		entity.setIdno(rs.getString("idno"));
		entity.setName(rs.getString("name"));
		entity.setPersonList(new ArrayList<WPerson>());
		return entity;
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private WPerson resultSetToWPerson(ResultSet rs) throws SQLException {
		WPerson person = new WPerson();
		person.setId(rs.getLong("id"));
		person.setIdno(rs.getString("idno"));
		person.setName(rs.getString("name"));
		person.setCompanyId(rs.getLong("w_company_id"));
		person.setNotebooks(new ArrayList<Notebook>());
		return person;
	}

	/**
	 *
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Notebook resultSetToNotebook(ResultSet rs) throws SQLException {
		Notebook notebook = new Notebook();
		notebook.setId(rs.getLong("id"));
		notebook.setBrand(rs.getString("brand"));
		notebook.setSpecification(rs.getString("specification"));
		notebook.setQuotation(rs.getBigDecimal("quotation"));
		notebook.setWpersonId(rs.getLong("w_person_id"));
		return notebook;
	}

	/**
	 *
	 * @param wPersons
	 * @param dbWPersons
	 * @throws SQLException
	 */
	private void saveWPersons(Collection<WPerson> wPersons, Collection<WPerson> dbWPersons) throws SQLException {

		if(dbWPersons != null) {
			for (WPerson dbPerson : dbWPersons) {
				boolean needDelete = true;
				if (wPersons != null) {
					for (WPerson person : wPersons) {
						if (person.getId() == dbPerson.getId()) {
							needDelete = false;
						}
					}
				}
				if (needDelete) {
					saveNotebooks(null, dbPerson.getNotebooks());
					deleteWPerson(dbPerson.getId());
				}
			}
		}

		if(wPersons != null) {
			for (WPerson person : wPersons) {
				if(person.getId() == null) {
					insertWPerson(person);
					person.getNotebooks().stream().forEach(notebook -> notebook.setWpersonId(person.getId()));
					saveNotebooks(person.getNotebooks(), null);
				} else {
					if (dbWPersons != null) {
						for (WPerson dbPerson : dbWPersons) {
							if (person.getId() == dbPerson.getId()) {
								updateWPerson(person);
								person.getNotebooks().stream().forEach(notebook -> notebook.setWpersonId(person.getId()));
								saveNotebooks(person.getNotebooks(), dbPerson.getNotebooks());
								break;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * insert wperson
	 * @param person
	 * @throws SQLException
	 */
	private void insertWPerson(WPerson person) throws SQLException {
		String personSql = "insert into w_person(idno, name, w_company_id) values ('" + person.getIdno() + "', '" + person.getName() + "'," + person.getCompanyId() + ");";
		Statement personStat = getConnection().createStatement();
		personStat.execute(personSql, Statement.RETURN_GENERATED_KEYS);
		ResultSet personRs = personStat.getGeneratedKeys();
		if (personRs.next()) {
			person.setId(personRs.getLong("id"));
		}
		log.info("personSql = " + personSql);
		log.info("perid = " + person.getId() + ", companyId = " + person.getCompanyId());
		personRs.close();
		personStat.close();
	}

	/**
	 * update wperson
	 * @param person
	 * @throws SQLException
	 */
	private void updateWPerson(WPerson person) throws SQLException {
		String personSql = " update w_person set idno = '" + person.getIdno() + "', " +
							" name = '" + person.getName() + "' " +
							" where id = " + person.getId() + ";";
		log.info(personSql);
		Statement personStat = getConnection().createStatement();
		personStat.executeUpdate(personSql);
		personStat.close();
	}

	/**
	 * delete wperson
	 * @param id
	 * @throws SQLException
	 */
	private void deleteWPerson(Long id) throws SQLException {
		String personSql = "delete from w_person where id = " + id;
		Statement personStat = getConnection().createStatement();
		log.info("delete personSql = " + personSql);
		personStat.executeUpdate(personSql);
		personStat.close();
	}

	/**
	 *
	 * @param nbs
	 * @param dbNbs
	 * @throws SQLException
	 */
	private void saveNotebooks(Collection<Notebook> nbs, Collection<Notebook> dbNbs) throws SQLException {

		if(dbNbs != null) {
			for (Notebook dbNb : dbNbs) {
				boolean needDelete = true;
				if (nbs != null) {
					for (Notebook nb : nbs) {
						if (nb.getId() == dbNb.getId()) {
							needDelete = false;
						}
					}
				}
				if (needDelete) {
					deleteNotebook(dbNb.getId());
				}
			}
		}

		if(nbs != null) {
			for (Notebook nb : nbs) {
				if(nb.getId() == null) {
					insertNotebook(nb);
				} else {
					if(dbNbs != null) {
						for (Notebook dbNb : dbNbs) {
							if(nb.getId() == dbNb.getId()) {
								updateNotebook(nb);
								break;
							}
						}
					}
				}
			}
		}
	}


	/**
	 * insert notebook
	 * @param nb
	 * @throws SQLException
	 */
	private void insertNotebook(Notebook nb) throws SQLException {
		String nbSql = "insert into notebook (brand, specification, quotation, w_person_id) values ('" + nb.getBrand() + "', '" + nb.getSpecification() + "', " + nb.getQuotation() + ", " + nb.getWpersonId() + ");";
		Statement nbStat = getConnection().createStatement();
		nbStat.execute(nbSql, Statement.RETURN_GENERATED_KEYS);
		ResultSet nbRs = nbStat.getGeneratedKeys();
		if (nbRs.next()) {
			nb.setId(nbRs.getLong("id"));
		}
		log.info("nbSql = " + nbSql);
		log.info("nb.getId = " + nb.getId());
		nbRs.close();
		nbStat.close();
	}

	/**
	 * update notebook
	 * @param nb
	 * @throws SQLException
	 */
	private void updateNotebook(Notebook nb) throws SQLException {
		String nbSql = " update notebook set brand = '" + nb.getBrand() + "', " +
				" specification = '" + nb.getSpecification() + "', " +
				" quotation = " + nb.getQuotation() +
				" where id = " + nb.getId();
		log.info(nbSql);
		Statement nbStat = getConnection().createStatement();
		nbStat.executeUpdate(nbSql);
		nbStat.close();
	}

	/**
	 * delete notebook
	 * @param id
	 * @throws SQLException
	 */
	private void deleteNotebook(Long id) throws SQLException {
		String nbSql = "delete from notebook where id = " + id;
		log.info("delete nbSql = " + nbSql);
		Statement nbStat = getConnection().createStatement();
		nbStat.executeUpdate(nbSql);
		nbStat.close();
	}

}
