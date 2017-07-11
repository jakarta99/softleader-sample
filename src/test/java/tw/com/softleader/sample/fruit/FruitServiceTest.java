package tw.com.softleader.sample.fruit;

import java.util.Collection;
import org.junit.Test;

public class FruitServiceTest {
	private FruitService fruitservice = new FruitService();

	@Test
	public void testGetAll() {
		Collection<Fruit> fruits = fruitservice.getAll();
		for (Fruit fruit : fruits) {
			System.out.println(fruit);
		}

	}

//	@Test
//	public void testgetOne() {
//		System.out.println(fruitservice.getOne(3L));
//	}

	@Test
	public void testinsertupdatadelete() {
		Fruit insert = new Fruit();
		insert.setName("Apple");
		insert.setColor("Red");
		fruitservice.insert(insert);

		Fruit update = new Fruit();
		update.setId(2L);
		update.setName("coconut");
		update.setColor("White");
		fruitservice.update(update);

		fruitservice.delete(5L);
	}

}
