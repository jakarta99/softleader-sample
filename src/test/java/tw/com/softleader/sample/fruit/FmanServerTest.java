package tw.com.softleader.sample.fruit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class FmanServerTest {

	private Logger log = Logger.getLogger(this.getClass());
	
	FmenSrevice fmanService = new FmenSrevice();
	
	FruitService fruitService = new FruitService();

	@Test
	public void testGetOne() {
		Fman fman = new Fman();
		fman = fmanService.getOne(1L);

		log.debug("testGetOne(1L): {}" + fman);
	}
	
	@Test
	public void testGetAll() {
		Collection<Fman> fmans = fmanService.getAll();
		
//		log.debug(fmans);
		log.debug("fman.size():" + fmans.size());
	}
	
	@Test
	public void testCRUD() {
		
		FruitDao fruitDao = new FruitDao();
		
		Fman insertFman = new Fman();
		Fruit fruit= new Fruit();
		Collection<Fruit> fruits = new ArrayList<Fruit>();
		insertFman.setName("Apple");
		insertFman.setIDno("1");
		
		fruit = fruitService.getOne(3L);  
		fruits.add(fruit);
		insertFman.setFruits(fruits); 
		
		fmanService.insert(insertFman);
		
		Long generatedId = insertFman.getId();
		log.debug("insert generatedId:" + generatedId);
		
		//檢查是否有新增
		Fman fman = fmanService.getOne(generatedId);
		
		log.debug("get fruit's id: " + fman.getFruits().iterator().next().getId());
		
		Fruit insertFruits = new Fruit();
		insertFruits = fruitDao.findOne(fman.getFruits().iterator().next().getId());
		
//		assertEquals("Green", insertFruits.getName());
		
		
		/** update*/
		fruits.clear();
		fruit = fruitService.getOne(2L);
		fruits.add(fruit);
		insertFman.setFruits(fruits); 
		
		fmanService.update(insertFman);
		
		//檢查是否有修改
		fman = fmanService.getOne(generatedId);
		
		insertFruits = fruitDao.findOne(fman.getFruits().iterator().next().getId());
		
//		assertEquals("Black", insertColors.getName());
		
		/** delete*/
		fmanService.delete(generatedId); //DELET JUST INSERT AND UPDATE
		
		//檢查是否有刪除
		fman = fmanService.getOne(generatedId);
		
		
//		assertNull(hperson);

	}
}

