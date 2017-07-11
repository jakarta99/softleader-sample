package tw.com.softleader.sample.cloth;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 飲料的服務
 * 
 * @author Gary Lee
 *
 */
public class ClothService implements GenericService<Cloth> {

	@Override
	public Cloth getOne(Long id) {
		ClothDao drinkDao = new ClothDao();
		return drinkDao.findOne(id);
	}

	@Override
	public Collection<Cloth> getAll() {

		ClothDao drinkDao = new ClothDao();
		
		return drinkDao.findAll();
	}

	@Override
	public void insert(Cloth entity) {
		ClothDao drinkDao = new ClothDao();
		drinkDao.insert(entity);
	}

	@Override
	public void update(Cloth entity) {
		ClothDao drinkDao = new ClothDao();
		drinkDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		ClothDao drinkDao = new ClothDao();
		drinkDao.delete(id);
		
	}
	
	
}
