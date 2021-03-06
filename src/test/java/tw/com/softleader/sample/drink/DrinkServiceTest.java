package tw.com.softleader.sample.drink;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrinkServiceTest {
	
	private Logger log = LoggerFactory.getLogger(DrinkServiceTest.class);
	
	private DrinkServiceImpl drinkService = new DrinkServiceImpl();

	@Test
	public void testCrud() {
		Collection<Drink> drinks = drinkService.getAll();
		int originalSize = drinks.size();
		
		
		// To construct a new object(drink) and insert into database
		Drink myFavoriteDrink = new Drink();
		myFavoriteDrink.setName("AppleTea");
		myFavoriteDrink.setColor("Pink");
		
		drinkService.insert(myFavoriteDrink);
		log.debug("{}", myFavoriteDrink);
		
		Long generatedId = myFavoriteDrink.getId(); // Id was generated by database native.
		
		
		// Try to modify the data
		myFavoriteDrink.setColor("Yellow");
		drinkService.update(myFavoriteDrink);
		
		
		Drink dbEntity = drinkService.getOne(generatedId);
		
		assertEquals("AppleTea", dbEntity.getName());
		assertEquals("Yellow", dbEntity.getColor());
		
		
		// To delete the drink that you construct before
		drinkService.delete(generatedId);
		assertNull(drinkService.getOne(generatedId));
		
		
		// check the final size should equals to original size
		drinks = drinkService.getAll();
		int finalSize = drinks.size();
		
		
		assertEquals(originalSize, finalSize);
		
	}



	
}
