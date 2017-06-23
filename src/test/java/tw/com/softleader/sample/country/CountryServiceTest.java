package tw.com.softleader.sample.country;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CountryServiceTest {

	CountryService countryService = new CountryService();
	private List<Country> countries = countryService.getAll();

	@Test
	public void testGetAll() {

		assertEquals(countryService.getOne(1).getName(), "Taiwan");
		assertEquals(countryService.getOne(1).getId(), 1);
		assertEquals(countryService.getOne(1).getSize(), "Small");

		assertEquals(countryService.getOne(2).getName(), "Japan");
		assertEquals(countryService.getOne(2).getId(), 2);
		assertEquals(countryService.getOne(2).getSize(), "Medium");

		assertEquals(countryService.getOne(3).getName(), "Malaysia");
		assertEquals(countryService.getOne(3).getId(), 3);
		assertEquals(countryService.getOne(3).getSize(), "Big");

	}

	@Test
	public void testGetOne() {

		assertEquals(countryService.getOne(1).getName(), "Taiwan");
		assertEquals(countryService.getOne(1).getId(), 1);
		assertEquals(countryService.getOne(1).getSize(), "Small");

	}

	@Test
	public void testInsertAndTestUpdateAndTestDelete() {

		Country newCountry = new Country();
		newCountry.setName("Hong Kong");
		newCountry.setId(countries.size() + 1);
		newCountry.setSize("Tiny");

		countryService.insert(newCountry);
		assertEquals(countryService.getOne(4).getName(), "Hong Kong");
		assertEquals(countryService.getOne(4).getId(), 4);
		assertEquals(countryService.getOne(4).getSize(), "Tiny");
		assertEquals(countries.size(), 4);

		Country update = new Country();
		update.setName("Korea");
		update.setSize("Medium");
		update.setId(4);
		update.setId(newCountry.getId());

		countryService.update(update);
		assertEquals(countryService.getOne(4).getName(), "Korea");
		assertEquals(countryService.getOne(4).getId(), 4);
		assertEquals(countryService.getOne(4).getSize(), "Medium");
		assertEquals(countries.size(), 4);

		countryService.delete(4);
		assertEquals(countries.size(), 3);

	}
}
