
package tw.com.softleader.sample.fruit;

import java.util.ArrayList;
import java.util.List;
import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 水果的服務
 * 
 * @author Howard
 *
 */

public class FruitService implements GenericService<Fruit> {
	private List<Fruit> fruits = new ArrayList<Fruit>();

	public FruitService() {
		Fruit fruit1 = new Fruit();
		fruit1.setId(1);
		fruit1.setName("apple");
		fruit1.setColor("red");

		Fruit fruit2 = new Fruit();
		fruit2.setId(2);
		fruit2.setName("pineapple");
		fruit2.setColor("yellow");

		Fruit fruit3 = new Fruit();
		fruit3.setId(3);
		fruit3.setName("kiwi");
		fruit3.setColor("green");

		fruits.add(fruit1);
		fruits.add(fruit2);
		fruits.add(fruit3);

	}

	@Override
	public Fruit getOne(int id) {
		int id2 = 0;
		for (int i = 0; i<fruits.size(); i++) {
			if (fruits.get(i).getId() == id) {
				id2 = i;
			}

		}

		return fruits.get(id2);
	}

	@Override
	public List<Fruit> getAll() {
		return fruits;
	}

	@Override
	public void insert(Fruit data) {
		fruits.add(data);

	}

	@Override
	public void update(Fruit data) {
		int id2 = data.getId();
		for (int i = 0; i<fruits.size(); i++) {
			if (fruits.get(i).getId() == id2) {
				id2 = i;
			}

		}
		fruits.set(id2, data);
		
	}

	@Override
	public void delete(int id) {
		fruits.remove(id);

	}

}