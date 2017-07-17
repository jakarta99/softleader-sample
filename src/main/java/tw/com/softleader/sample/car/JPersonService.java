package tw.com.softleader.sample.car;

import java.util.Collection;
import java.util.Optional;

import tw.com.softleader.sample.commons.GenericService;

/**
 * 
 * 人的服務
 * 
 * @author Gary Lee
 *
 */
public class JPersonService implements GenericService<JPerson> {

	@Override
	public JPerson getOne(Long id) {
		JPersonDao jPersonDao = new JPersonDao();
		
		Optional<JPerson> jPerson = Optional.ofNullable(jPersonDao.findOne(id));
		
		jPerson.ifPresent(p -> {
			CarDao carDao = new CarDao();
			Collection<Car> cars = carDao.findByJPersonId(id);
			p.setCars(cars);
		});
		
		return jPerson.isPresent() ? jPerson.get() : null;
	}

	@Override
	public Collection<JPerson> getAll() {
		JPersonDao jPersonDao = new JPersonDao();
		Collection<JPerson> jPeople = jPersonDao.findAll();
		if (jPeople != null && jPeople.size() > 0) {
			CarDao carDao = new CarDao();
			jPeople.forEach(p -> p.setCars(carDao.findByJPersonId(p.getId())));
		}
		
		return jPeople;
	}

	@Override
	public void insert(JPerson entity) {
		JPersonDao jPersonDao = new JPersonDao();
		jPersonDao.insert(entity);
		
		Collection<Car> cars = entity.getCars();
		if (cars != null && cars.size() > 0) {
			CarService carService = new CarService();
			for (Car car : cars) {
				car.setjPersonId(entity.getId());
				carService.insert(car);
			}
		}
	}

	@Override
	public void update(JPerson entity) {
		JPersonDao jPersonDao = new JPersonDao();
		jPersonDao.update(entity);
		
		Collection<Car> cars = entity.getCars();
		if (cars != null && cars.size() > 0) {
			CarService carService = new CarService();
			for (Car car : cars) {
				if (car.getId() == null) {
					car.setjPersonId(entity.getId());
					carService.insert(car);
				} else {
					carService.update(car);
				}
			}
		}
	}

	@Override
	public void delete(Long id) {
		Optional<JPerson> jPerson = Optional.ofNullable(getOne(id));
		jPerson.ifPresent(p -> {
			CarService carService = new CarService();
			Collection<Car> cars = p.getCars();
			if (cars != null && !cars.isEmpty()) {
				cars.forEach(c -> carService.delete(c.getId()));
			}
		});
		
		JPersonDao jPersonDao = new JPersonDao();
		jPersonDao.delete(id);
	}
	
	
}
