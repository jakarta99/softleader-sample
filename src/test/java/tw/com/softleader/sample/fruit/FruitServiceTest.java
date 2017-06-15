package tw.com.softleader.sample.fruit;

import org.junit.Test;

public class FruitServiceTest {
	
	private void assertEquals(String fru, String fru1, String fru2, String fru3, String fru4,String [] fruits) {
	}
	@Test
	public void fruitServiceTest() {
		FruitService fruitService = new FruitService();
		for (String fruits : fruitService.getAll()) {
			assertEquals("西瓜","蘋果","芭樂","柳丁","橘子", fruitService.getAll());
		}
	}

}
