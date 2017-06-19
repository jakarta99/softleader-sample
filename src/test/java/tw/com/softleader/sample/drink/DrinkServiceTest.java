package tw.com.softleader.sample.drink;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DrinkServiceTest {
	
	private DrinkService drinkService = new DrinkService();

	@Test
	public void testGetAll() {
		List<Drink> drinks = drinkService.getAll();
		
		assertEquals(drinks.get(0).getName(), "coffee");
		assertEquals(drinks.get(0).getColor(), "black");
		
	}




	
}
