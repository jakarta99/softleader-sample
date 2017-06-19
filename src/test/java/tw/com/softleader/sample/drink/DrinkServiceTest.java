package tw.com.softleader.sample.drink;

import static org.junit.Assert.fail;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DrinkServiceTest {
	
	private DrinkService drinkService = new DrinkService();

	@Test
	public void testGetAll() {
		//drinkService.getAll();
	}

	@Test
	public void testGetOne() {
		assertEquals("coffee", drinkService.getOne(0));
	}

	@Test
	public void testInsertAndTestUpdateAndTestDelete() {
		drinkService.insert("milk");
		for(String drink:drinkService.getAll()) {
			System.out.println(drink);
		}
		assertEquals("milk", drinkService.getOne(3));
		System.out.println("-----------------------");
		
		drinkService.update("water", 3);
		for(String drink:drinkService.getAll()) {
			System.out.println(drink);
		}
		assertEquals("water", drinkService.getOne(3));
		System.out.println("-----------------------");
		drinkService.delete("water");
		
		for(String drink:drinkService.getAll()) {
			System.out.println(drink);
		}
		
		assertEquals(3, drinkService.getAll().length);
	}

	
}
