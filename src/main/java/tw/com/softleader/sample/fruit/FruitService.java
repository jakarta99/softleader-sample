
package tw.com.softleader.sample.fruit;

import java.util.Collection;
import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 水果的服務
 * 
 * @author Howard
 *
 */

public class FruitService implements GenericService<Fruit> {
	FruitDao fruitDao = new FruitDao();

	@Override
	public Fruit getOne(Long id) {
		return fruitDao.findOne(id);
	}

	@Override
	public Collection<Fruit> getAll() {
		return fruitDao.findAll();
	}

	@Override
	public void insert(Fruit entity) {
		fruitDao.insert(entity);
	}

	@Override
	public void update(Fruit entity) {
		fruitDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		fruitDao.delete(id);
	}
	
}