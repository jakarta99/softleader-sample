package tw.com.softleader.sample.country;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import tw.com.softleader.sample.country.*;

public class CountryServiceTest {
	CountryService countryService = new CountryService();
	private List<Country> countries = new ArrayList<Country>();
		List<Country> HongKong=null,Singapore=null,Taiwan=null,Japan=null,Malaysia=null;
	@Test
	public void testGetAll() {
		assertEquals((Country)Taiwan,countryService.getOne(0));
		assertEquals((Country)Japan,countryService.getOne(1));
		assertEquals((Country)Malaysia,countryService.getOne(2));
	}

	@Test
	public void testGetOne() {
		assertEquals((Country)Taiwan, countryService.getOne(0));
	}

	@Test
	public void testInsertAndTestUpdateAndTestDelete()throws Exception {



		countries.add((Country) HongKong);
		for (Country print:countries) {
			System.out.println(print);
		}
		assertEquals("HongKong", countryService.getOne(3));
		System.out.println("-----------------------");

		countryService.update((Country)Singapore);
		for (Country print:countries) {
			System.out.println(print);
		}
		assertEquals("Singapore", countryService.getOne(3));
		System.out.println("-----------------------");
		countryService.delete(3);
		for (Country print:countries) {
			System.out.println(print);
		}
		assertEquals(3, countries.size());
	}

}
