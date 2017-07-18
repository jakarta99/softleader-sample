package tw.com.softleader.sample.drink;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class DrinkServiceImpl implements DrinkService {

	@Override
	public Drink getOne(Long id) {
		DrinkDao drinkDao = new DrinkDao();
		return drinkDao.findOne(id);
	}

	@Override
	public Collection<Drink> getAll() {

		DrinkDao drinkDao = new DrinkDao();
		
		return drinkDao.findAll();
	}

	@Override
	public void insert(Drink entity) {
		DrinkDao drinkDao = new DrinkDao();
		drinkDao.insert(entity);
	}

	@Override
	public void update(Drink entity) {
		DrinkDao drinkDao = new DrinkDao();
		drinkDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		DrinkDao drinkDao = new DrinkDao();
		drinkDao.delete(id);
		
	}
	
	
}
