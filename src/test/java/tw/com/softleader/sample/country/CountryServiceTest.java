package tw.com.softleader.sample.country;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tw.com.softleader.sample.country.CountryService;

public class CountryServiceTest {
	@Test
	public void countryServiceTest() {
		CountryService countryService = new CountryService();
		String[] country = countryService.getAll();

		assertEquals("Taiwan", country[0]);
		assertEquals("Thailand", country[1]);
		assertEquals("Japan", country[2]);
	}
}
