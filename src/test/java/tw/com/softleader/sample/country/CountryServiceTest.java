package tw.com.softleader.sample.country;

import java.util.Collection;

import org.junit.Test;

public class CountryServiceTest {

	CountryService countryService = new CountryService();

	@Test
	public void testGetOne() {
		System.out.println(countryService.getOne(2l));
	}

	@Test
	public void testGetAll() {
		Collection<Country> countries = countryService.getAll();
		for (Country country : countries) {
			System.out.println(country);
		}
	}

	@Test
	public void testInsertUpdateDelete() {
		Country insertNew = new Country();
		insertNew.setId(3l);
		insertNew.setName("Singapore");
		insertNew.setSize("Tiny");
		countryService.insert(insertNew);

		Country update = new Country();
		update.setId(3l);
		update.setName("Japan");
		update.setSize("Medium");
		countryService.update(update);

		countryService.delete(3l);
	}
	// @Test
	// public void testInsert() {
	// Country insertNew = new Country();
	// insertNew.setId(3l);
	// insertNew.setName("Singapore");
	// insertNew.setSize("Tiny");
	// countryService.insert(insertNew);
	// }
	//
	// @Test
	// public void testUpdate() {
	// Country update = new Country();
	// update.setId(3l);
	// update.setName("Japan");
	// update.setSize("Medium");
	// countryService.update(update);
	// }
	//
	// @Test
	// public void testDelete() {
	// countryService.delete(3l);
	// }

}
