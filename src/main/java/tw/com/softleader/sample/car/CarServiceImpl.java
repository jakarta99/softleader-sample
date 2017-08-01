package tw.com.softleader.sample.car;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 汽車的服務
 * 
 * @author Benny
 *
 */
@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarDao carDao;
	
	private String defaultColor = "Yellow";
	
	public CarServiceImpl(){
		
	}
	
	public CarServiceImpl(String defaultColor){
		this.defaultColor = defaultColor;
	}

	@Override
	public Car getOne(Long id) {
		CarDao carDao = new CarDao();
		return carDao.findOne(id);
	}

	@Override
	public Collection<Car> getAll() {

//		CarDao carDao = new CarDao();
		
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

	public String getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(String defaultColor) {
		this.defaultColor = defaultColor;
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
