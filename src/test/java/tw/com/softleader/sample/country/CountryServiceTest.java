package tw.com.softleader.sample.country;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CountryServiceTest {

	CountryService countryService = new CountryService();
	private List<Country> countries = countryService.getAll();

	@Test
	public void testGetAll() {

		assertEquals(countries.get(0).getName(), "Taiwan");
		assertEquals(countries.get(1).getName(), "Japan");
		assertEquals(countries.get(2).getName(), "Malaysia");
	}

	@Test
	public void testGetOne() {

		int i = 0;
		assertEquals(countryService.getOne(i).getName(), "Taiwan");
	}

	@Test
	public void testInsertAndTestUpdateAndTestDelete() {

		Country newCountry = new Country();
		newCountry.setName("Hong Kong");
		newCountry.setId(countries.size() + 1);
		countryService.insert(newCountry);
		assertEquals(countries.get(3).getName(), "Hong Kong");
		System.out.println(countries.get(3).getName());

		Country update = new Country();
		update.setName("Korea");
		countryService.update(update);
		assertEquals(countryService.getOne(3).getName(), "Korea");
		System.out.println(countryService.getOne(3).getName());

		countryService.delete(countries.size() - 1);
		assertEquals(countries.size(), 3);
		System.out.println(countries.size());

	}
}
