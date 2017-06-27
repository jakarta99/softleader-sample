package tw.com.softleader.sample.drink;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

public class DrinkServiceTest {
	
	private DrinkService drinkService = new DrinkService();

	@Test
	public void testGetAll() {
		
		
		Collection<Drink> drinks = drinkService.getAll();
		
		for(Drink drink:drinks) {
			System.out.println(drink);
		}
		
	}



	
}
