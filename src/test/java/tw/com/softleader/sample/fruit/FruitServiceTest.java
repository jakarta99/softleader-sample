package tw.com.softleader.sample.fruit;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class FruitServiceTest {
	private FruitService fruitservice = new FruitService();
	List<Fruit> fruitsss = fruitservice.getAll();

	@Test
	public void testGetAll() {
		assertEquals(fruitsss.get(0).getId(), 1);
		assertEquals(fruitsss.get(0).getName(), "apple");
		assertEquals(fruitsss.get(0).getColor(), "red");
		assertEquals(fruitsss.get(1).getId(), 2);
		assertEquals(fruitsss.get(1).getName(), "pineapple");
		assertEquals(fruitsss.get(1).getColor(), "yellow");
		assertEquals(fruitsss.get(2).getId(), 3);
		assertEquals(fruitsss.get(2).getName(), "kiwi");
		assertEquals(fruitsss.get(2).getColor(), "green");
	}

	@Test
	public void testgetOne() {
		Fruit fruits = fruitservice.getOne(1);

		assertEquals(fruits.getId(), 1);
//		assertEquals(fruits.getName(), "apple");
//		assertEquals(fruits.getColor(), "red");

		// assertEquals(fruits.getName(), "pineapple");

	}

	@Test
	public void testinsertupdatadelete() {
		Fruit fruitad = new Fruit();
		fruitad.setId(4);
		fruitad.setName("coconut");
		fruitad.setColor("white");
		fruitservice.insert(fruitad);
		assertEquals(fruitsss.get(3).getColor(), "white");
		//資料已存在 exception?

		Fruit fruitup = new Fruit();
		fruitup.setId(4);
		fruitup.setName("cherry");
		fruitup.setColor("black");
		fruitservice.update(fruitup);
		assertEquals(fruitsss.get(3).getColor(), "black");

		fruitservice.delete(3);
		assertEquals(fruitsss.size(), 3);
	}

}
