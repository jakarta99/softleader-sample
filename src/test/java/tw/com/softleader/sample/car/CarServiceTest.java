package tw.com.softleader.sample.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.car.Car;
import tw.com.softleader.sample.car.CarService;

public class CarServiceTest {
	
	private Logger log = LoggerFactory.getLogger(CarServiceTest.class);
	
	private CarService CarService = new CarService();

	@Test
	public void testCrud() {
		Collection<Car> cars = CarService.getAll();
		int originalSize = cars.size();
		
		log.debug("size : " + originalSize);
		// To construct a new object(Car) and insert into database
		Car myFavoriteCar = new Car();
		myFavoriteCar.setName("Model X");
		myFavoriteCar.setColor("Pink");
		
		CarService.insert(myFavoriteCar);
		log.debug("{}", myFavoriteCar);
		
		Long generatedId = myFavoriteCar.getId(); // Id was generated by database native.
		
		// Try to modify the data
		myFavoriteCar.setColor("Yellow");
		CarService.update(myFavoriteCar);
		
		Car dbEntity = CarService.getOne(generatedId);
		
		assertEquals("Model X", dbEntity.getName());
		assertEquals("Yellow", dbEntity.getColor());
		
		// To delete the Car that you construct before
		CarService.delete(generatedId);
		assertNull(CarService.getOne(generatedId));
		
		// check the final size should equals to original size
		cars = CarService.getAll();
		int finalSize = cars.size();
		
		assertEquals(originalSize, finalSize);
	}



	
}
