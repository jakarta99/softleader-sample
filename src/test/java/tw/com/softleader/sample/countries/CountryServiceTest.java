import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
import org.junit.Test;
public class CountryServiceTest {


	@Test
	public void countryServiceTest() {
		CountryService countryService = new CountryService();
			assertEquals("Taiwan", countryService.countries[0]);
			assertEquals("Taipei", countryService.countries[1]);
			assertEquals("Japan", countryService.countries[2]);
	}

}
