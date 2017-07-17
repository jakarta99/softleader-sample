package tw.com.softleader.sample.car;

import java.util.Collection;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 汽車的服務
 * 
 * @author Benny
 *
 */
public class CarService implements GenericService<Car> {

	@Override
	public Car getOne(Long id) {
		CarDao carDao = new CarDao();
		return carDao.findOne(id);
	}

	@Override
	public Collection<Car> getAll() {

		CarDao carDao = new CarDao();
		
		return carDao.findAll();
	}

	@Override
	public void insert(Car entity) {
		CarDao carDao = new CarDao();
		carDao.insert(entity);
	}

	@Override
	public void update(Car entity) {
		CarDao carDao = new CarDao();
		carDao.update(entity);
		
	}

	@Override
	public void delete(Long id) {
		CarDao carDao = new CarDao();
		carDao.delete(id);
		
	}
	
//	public Collection<Car> findByJPersonId(Long jPersonId) {
//		CarDao carDao = new CarDao();
//		return carDao.findByJPersonId(jPersonId);
//	}
	
//	public void deleteByJPersonId(Long jPersonId) {
//		CarDao carDao = new CarDao();
//		carDao.deleteByJPersonId(jPersonId);
//	}
	
}
