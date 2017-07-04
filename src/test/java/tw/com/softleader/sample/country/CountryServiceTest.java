package tw.com.softleader.sample.country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;

import tw.com.softleader.sample.commons.DataSourceUtil;

public class CountryServiceTest {

	private Logger log = Logger.getLogger(this.getClass());

	CountryService countryService = new CountryService();

	@Test
	public void testGetOne() {
		try {
			DataSource ds = DataSourceUtil.getInstance().getDataSource();
			Connection connection = ds.getConnection();

			Statement stmt = connection.createStatement();
			String sqlCmd = "SELECT id FROM country ORDER BY RANDOM() LIMIT 1";
			ResultSet rs = stmt.executeQuery(sqlCmd);
			if (rs.next()) {
				log.info("2:testGetOne-->" + countryService.getOne(rs.getLong("id")));
			} else {
				log.info("2:testGetOne,DB has no data");
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAll() {
		Collection<Country> countries = countryService.getAll();
		for (Country country : countries) {
			log.info("2:testGetAll-->" + country);
		}
	}

	@Test
	public void testInsertUpdateDelete() {
		Country insertNew = new Country();
		insertNew.setName("Singapore");
		insertNew.setSize("Tiny");
		countryService.insert(insertNew);

		log.info("2:testInsertNew.getId-->" + insertNew.getId());

		Country update = new Country();
		update.setId(insertNew.getId());
		update.setName("Japan");
		update.setSize("Medium");
		countryService.update(update);

		countryService.delete(insertNew.getId());
	}

}
