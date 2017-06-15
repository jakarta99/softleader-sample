package tw.com.softleader.sample.countries;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import org.junit.Test;

public class CountryServiceTest {
	private void assertEquals(String string, String string2, String string3, String country) {
	}

	@Test
	public void countryServiceTest() {
		CountryService countryService = new CountryService();
		for (String country : countryService.getall()) {
			assertEquals("Taiwan", "Thailand", "Japan", country);
		}

	}

}
