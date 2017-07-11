package tw.com.softleader.sample.drink;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DPersonDaoTest {

	private Logger log = LoggerFactory.getLogger(DPersonDaoTest.class);
	
	private DPersonDao dPersonDao = new DPersonDao();
	
	@Test
	public void testCrud() {
		Collection<DPerson> persons = dPersonDao.findAll();
		int originalSize = persons.size();
		
		
		// To construct a new object(drink) and insert into database
		DPerson alice = new DPerson();
		alice.setIdno("A000000001");
		alice.setName("alice");
		
		Collection<Drink> aliceDrinks = new ArrayList<Drink>();
		Drink aliceDrink1 = new Drink();
		aliceDrink1.setName("AppleTea");
		aliceDrink1.setColor("Pink");
		aliceDrinks.add(aliceDrink1);
		
		Drink aliceDrink2 = new Drink();
		aliceDrink2.setName("Coffee");
		aliceDrink2.setColor("Black");
		aliceDrinks.add(aliceDrink2);
		
		Drink aliceDrink3 = new Drink();
		aliceDrink3.setName("Milk");
		aliceDrink3.setColor("White");
		aliceDrinks.add(aliceDrink3);
		
		alice.setDrinks(aliceDrinks);
		
		dPersonDao.insert(alice);
		log.debug("{}", alice);
		
		Long alicePersonId = alice.getId();
		
		
		// Try to modify the data
		DPerson aliceFromDB = dPersonDao.findOne(alicePersonId);
		
		Drink aliceDrink4 = new Drink();
		aliceDrink4.setName("Juice");
		aliceDrink4.setColor("Orange");
		aliceFromDB.getDrinks().add(aliceDrink4);
		
		
		// To delete the drink that you construct before
		dPersonDao.delete(alicePersonId);
		assertNull(dPersonDao.findOne(alicePersonId));
		
		
		// check the final size should equals to original size
		persons = dPersonDao.findAll();
		int finalSize = persons.size();
		
		
		assertEquals(originalSize, finalSize);
		
	}
	
	
	
	
}
