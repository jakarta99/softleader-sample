package tw.com.softleader.sample.fruit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FcompanyDaoTest {

	private Logger log = LoggerFactory.getLogger(FcompanyDaoTest.class);
	
	private FcompanyDao fcompanyDao = new FcompanyDao();
	
	
	@Test
	public void testCrud() {
		Collection<Fcompany> coms = fcompanyDao.findAll();
		int originalSize = coms.size();

			
				Fcompany com = new Fcompany();
				com.setUno("12345678");
				com.setName("moneymuchworklessnearhome");
				
				Collection<Fman> mans = new ArrayList<Fman>();
				
				Fman fman1 = new Fman();
				fman1.setIDno("a123456789");
				fman1.setName("jack");
				
				Collection<Fruit> fruits = new ArrayList<>();
				
				Fruit fruit1 = new Fruit();
				fruit1.setName("apple");
				fruit1.setColor("red");
				fruits.add(fruit1);
				
				Fruit fruit2 = new Fruit();
				fruit2.setName("pineapple");
				fruit2.setColor("yellow");
				fruits.add(fruit2);
				fman1.setFruits(fruits);
				mans.add(fman1);
				
				
				Fman fman2 = new Fman();
				fman2.setIDno("b123456789");
				fman2.setName("mary");
				
				Collection<Fruit> fruits2 = new ArrayList<>();
				
				Fruit fruit3 = new Fruit();
				fruit3.setName("kiwi");
				fruit3.setColor("green");
				fruits2.add(fruit3);
				
				Fruit fruit4 = new Fruit();
				fruit4.setName("coconut");
				fruit4.setColor("while");
				fruits2.add(fruit4);
				
				Fruit fruit5 = new Fruit();
				fruit5.setName("berry");
				fruit5.setColor("blue");
				fruits2.add(fruit5);
				fman2.setFruits(fruits2);

				mans.add(fman2);
				com.setFmans(mans);
				fcompanyDao.insert(com);
				

				log.debug("{}", com);
				

		
		
		
		
		Long comid = com.getId();
		
		Fcompany comfromDB = fcompanyDao.findOne(comid);
		comfromDB.setUno("87655678");
		comfromDB.setName("moneylessworkmuchfarhome");
		
		Collection<Fman> mans2 = new ArrayList<>();
		
		Fman fman3 = new Fman();
		fman3.setIDno("C123456789");
		fman3.setName("fool");
		
		Collection<Fruit> fruits3 = new ArrayList<>();
		
		Fruit fruit6 = new Fruit();
		fruit6.setName("cheapApple");
		fruit6.setColor("black");
		
		fruits3.add(fruit6);
		fman3.setFruits(fruits3);
		mans2.add(fman3);
		comfromDB.setFmans(mans2);
		
		fcompanyDao.update(comfromDB);
		
		fcompanyDao.delete(comid+1);

		assertNull(fcompanyDao.findOne(comid+1));
		
		coms = fcompanyDao.findAll();
		int finalSize = coms.size();
				
		assertEquals(originalSize, finalSize);
		
	}
	
	
	
	
}
