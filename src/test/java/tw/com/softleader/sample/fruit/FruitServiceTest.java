package tw.com.softleader.sample.fruit;

import static org.junit.Assert.*;

import org.junit.Test;

public class FruitServiceTest {
	
	@SuppressWarnings("deprecation")
	@Test
	public void fruitServiceTest() {
		String[] compair = {"西瓜","蘋果","芭樂","柳丁","橘子" };
		FruitService fruitService = new FruitService();
			assertEquals(compair, fruitService.getAll());
		
	}


}
