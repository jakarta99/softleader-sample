package tw.com.softleader.sample.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JCompanyServiceTest {
	
	private Logger log = LoggerFactory.getLogger(JCompanyServiceTest.class);
	
	private JCompanyService jCompanyService = new JCompanyService();
	private JPersonService jPersonService = new JPersonService();
	private CarService carService = new CarServiceImpl();

	@Test
	public void testCrud() {
		Collection<JCompany> originJCompanies = jCompanyService.getAll();
		Collection<JPerson> originJPeople = jPersonService.getAll();
		Collection<Car> originCars = carService.getAll();
		
		log.info(" insert start"); 
		JCompany jCompany = new JCompany();
		jCompany.setName("SL");
		jCompany.setEnglishName("Softleader");
		jCompany.setUniformNumber("80230237");
		
		Collection<JPerson> jPeople = new ArrayList<>();
		JPerson jPerson = new JPerson();
		jPerson.setIdNo("ID000001");
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
		jPeople.add(jPerson);
		jCompany.setjPeople(jPeople);
		jCompanyService.insert(jCompany);
		log.info(" insert end"); 
		
		//update
		log.info(" update start"); 
		Long newJCompanyId = jCompany.getId();
		Long newJPersonId = jPerson.getId();
		Long newCar2Id = car2.getId();
		jCompany.setName("SoftLeader");
		jPerson.setIdNo("J00002");
		car2.setColor("Black");
		
		jCompanyService.update(jCompany);
		
		JCompany dbJCompany = jCompanyService.getOne(newJCompanyId);
		
		assertEquals("SoftLeader", dbJCompany.getName());
		
		Collection<JPerson> dbJPeople = dbJCompany.getjPeople();
		dbJPeople.stream()
		.filter(p -> newJPersonId.equals(p.getId()))
		.forEach(p -> {
			
			assertEquals("J00002", p.getIdNo());
			Collection<Car> dbCars = p.getCars();
			
			dbCars.stream().filter(c -> newCar2Id.equals(c.getId()))
			.forEach(c -> assertEquals("Black", c.getColor()));
		});
		
		log.info(" update end"); 
		
		//delete
		log.info(" delete start"); 
		jCompanyService.delete(newJCompanyId);
		assertNull(jCompanyService.getOne(newJCompanyId));
		Collection<JCompany> finalJCompanies = jCompanyService.getAll();
		Collection<JPerson> finalJPeople = jPersonService.getAll();
		Collection<Car> finalCars = carService.getAll();
		
		assertEquals(originJCompanies.size(), finalJCompanies.size());
		assertEquals(originJPeople.size(), finalJPeople.size());
		assertEquals(originCars.size(), finalCars.size());
		
		log.info(" delete end"); 
	}

}
