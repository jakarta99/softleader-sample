package tw.com.softleader.sample.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPersonServiceTest {
	
	private Logger log = LoggerFactory.getLogger(JPersonServiceTest.class);
	
	private JPersonService jPersonService = new JPersonService();
	private CarService carService = new CarServiceImpl();

	@Test
	public void testCrud() {
		Collection<JPerson> originJPeople = jPersonService.getAll();
		Collection<Car> originCars = carService.getAll();
		
		log.info(" insert start"); 
		JPerson jPerson = new JPerson();
		jPerson.setIdNo("J00001");
		jPerson.setName("Benny");
		
		Collection<Car> cars = new ArrayList<>();
		Car car1 = new Car();
		car1.setBrand("Lamborghini");
		car1.setName("diablo");
		car1.setColor("Yellow");
		cars.add(car1);
		
		Car car2 = new Car();
		car2.setBrand("Mercedes-Benz");
		car2.setName("G65 AMG");
		car2.setColor("Sliver");
		cars.add(car2);
		
		jPerson.setCars(cars);
		jPersonService.insert(jPerson);
		log.info(" insert end"); 
		
		//update
		log.info(" update start"); 
		Long newJPersonId = jPerson.getId();
		Long updateCarId = car2.getId();
		jPerson.setIdNo("J00002");
		car2.setColor("Black");
		
		jPersonService.update(jPerson);
		
		JPerson dbJPerson = jPersonService.getOne(newJPersonId);
		
		assertEquals("J00002", dbJPerson.getIdNo());
		
		Collection<Car> dbCars = dbJPerson.getCars();
		for (Car car : dbCars) {
			if(updateCarId.equals(car.getId())){
				assertEquals("Black", car.getColor());
			}
		}
		log.info(" update end"); 
		
		//delete
		log.info(" delete start"); 
		jPersonService.delete(newJPersonId);
		assertNull(jPersonService.getOne(newJPersonId));
		
		Collection<JPerson> finalJPeople = jPersonService.getAll();
		Collection<Car> finalCars = carService.getAll();
		
		assertEquals(originJPeople.size(), finalJPeople.size());
		assertEquals(originCars.size(), finalCars.size());
		
		log.info(" delete end"); 
	}

}
