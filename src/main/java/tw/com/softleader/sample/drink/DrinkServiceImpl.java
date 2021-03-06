package tw.com.softleader.sample.drink;

import java.util.Collection;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class DrinkServiceImpl implements DrinkService {
	
	private String defaultColor = "yellow";
	
	public DrinkServiceImpl() {
		
	}
	
	public DrinkServiceImpl(String color) {
		this.defaultColor = color;
	}
	
	

	@Override
	public Drink getOne(Long id) {
		DrinkDao drinkDao = new DrinkDao();
		return drinkDao.findOne(id);
	}

	@Override
	public Collection<Drink> getAll() {

		System.out.println(defaultColor);
		
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
