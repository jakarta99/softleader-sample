package tw.com.softleader.sample.fruit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FmanDaoTest {

	private Logger log = LoggerFactory.getLogger(FmanDaoTest.class);
	
	private FmanDao fmanDao = new FmanDao();
	
	@Test
	public void testCrud() {
		Collection<Fman> fmans = fmanDao.findAll();
		int originalSize = fmans.size();
		
		
		// To construct a new object(drink) and insert into database
		Fman alice = new Fman();
		alice.setIDno("A000000001");
		alice.setName("alice");
		
		Collection<Fruit> aliceFruits = new ArrayList<Fruit>();
		Fruit aliceFruit1 = new Fruit();
		aliceFruit1.setName("AppleTea");
		aliceFruit1.setColor("Pink");
		aliceFruits.add(aliceFruit1);
		
		Fruit aliceFruit2 = new Fruit();
		aliceFruit2.setName("Coffee");
		aliceFruit2.setColor("Black");
		aliceFruits.add(aliceFruit2);
		
		Fruit aliceFruit3 = new Fruit();
		aliceFruit3.setName("Milk");
		aliceFruit3.setColor("White");
		aliceFruits.add(aliceFruit3);
		
		alice.setFruits(aliceFruits);
		
		fmanDao.insert(alice);
		log.debug("{}", alice);
		
		Long alicePersonId = alice.getId();
		
		
		// Try to modify the data
		Fman aliceFromDB = fmanDao.findOne(alicePersonId);//先找出該人

		Fruit aliceDrink4 = new Fruit();
		aliceDrink4.setName("Juice");
		aliceDrink4.setColor("Orange");
//		aliceDrink4.setPid(alicePersonId);
		aliceFromDB.getFruits().add(aliceDrink4);
		
		log.debug("{}", aliceFromDB);
		fmanDao.update(aliceFromDB);
		
		log.debug("alicePersonId"+alicePersonId);
		
		
		
		
		// To delete the drink that you construct before
		fmanDao.delete(alicePersonId+1);
		assertNull(fmanDao.findOne(alicePersonId+1));
		
		
		// check the final size should equals to original size
		fmans = fmanDao.findAll();
		int finalSize = fmans.size();
		
		
		assertEquals(originalSize, finalSize);
		
	}
	
	
	
	
}
