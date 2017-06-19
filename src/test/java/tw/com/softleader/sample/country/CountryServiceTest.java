package tw.com.softleader.sample.country;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tw.com.softleader.sample.country.CountryService;

public class CountryServiceTest {

	private CountryService countryService = new CountryService();

	@Test
	public void testGetAll() {
		// countryService.getAll();
	}

	@Test
	public void testGetOne() {
		assertEquals("Taiwan", countryService.getOne(0));
	}

	@Test
	public void testInsertAndTestUpdateAndTestDelete() {
		countryService.insert("HongKong");
		for (String country : countryService.getAll()) {
			System.out.println(country);
		}
		assertEquals("HongKong", countryService.getOne(3));
		System.out.println("-----------------------");

		countryService.update("Singapore", 3);
		for (String country : countryService.getAll()) {
			System.out.println(country);
		}
		assertEquals("Singapore", countryService.getOne(3));
		System.out.println("-----------------------");
		countryService.delete("Singapore");

		for (String country : countryService.getAll()) {
			System.out.println(country);
		}

		assertEquals(3, countryService.getAll().length);
	}

}
