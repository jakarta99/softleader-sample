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
public class DrinkService implements GenericService<Drink> {

	@Override
	public Drink getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Drink> getAll() {

		DrinkDao drinkDao = new DrinkDao();
		
		return drinkDao.findAll();
	}

	@Override
	public void insert(Drink entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Drink entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
