package tw.com.softleader.sample.country;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CountryServiceTest {

	private Logger log = Logger.getLogger(this.getClass());

	CountryService countryService = new CountryService();

	@Test
	public void testGetOne() {
		log.info("2:testGetOne-->" + countryService.getOne(5L));
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
