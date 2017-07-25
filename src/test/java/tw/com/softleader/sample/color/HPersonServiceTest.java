package tw.com.softleader.sample.color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

import tw.com.softleader.sample.drink.DPerson;
import tw.com.softleader.sample.drink.Drink;

public class HPersonServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	HPersonService hPersonService = new HPersonService();
	
	ColorServiceImpl colorService = new ColorServiceImpl();
	
	ColorDao colorDao = new ColorDao();
	
	HPersonDao dao= new HPersonDao();

	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCRUD() {
		
		Collection<HPerson> persons = dao.findAll();
		int originalSize = persons.size();
		
		HPerson insertHperson = new HPerson();
		insertHperson.setName("Candy");
		insertHperson.setIdNo("C123456789");
		
		Collection<Color> colors = new ArrayList<Color>();
		// INSERT COLOR Green
		Color colorGreen = new Color();
		colorGreen.setName("Green");
		colorGreen.setCode("#00FF00");
		colors.add(colorGreen);
		
		// INSERT COLOR Red
		Color colorRed = new Color();
		colorRed.setName("Red");
		colorRed.setCode("#FF0000");
		colors.add(colorRed);
		
		// INSERT COLOR White
		Color colorWhite = new Color();
		colorWhite.setName("White");
		colorWhite.setCode("#000000");
		colors.add(colorWhite);
		
		insertHperson.setColors(colors); 
		
		dao.insert(insertHperson);
		
		Long generatedId = insertHperson.getId();
		log.debug("insert generatedId:" + generatedId);
		
		
		// Update
		HPerson updateHperson = dao.findOne(generatedId);
		log.debug("update generatedId:" + generatedId);
		updateHperson.setName("Bella");
		updateHperson.setIdNo("B211111111");
		
		Color colorBlack = new Color();
		colorBlack.setName("Black");
		colorBlack.setCode("#FFFFFF");
		updateHperson.getColors().add(colorBlack);
		
		dao.update(updateHperson);
		
		
		// Delete
		dao.delete(generatedId);
		log.debug("delete generatedId:" + generatedId);
		//assertNull(dao.findOne(generatedId));
		
		
		// check the final size should equals to original size
		persons = dao.findAll();
		int finalSize = persons.size();
		
		assertEquals(originalSize, finalSize);
	}
	
	@Test
	public void testGetOne() {
		HPerson hperson = new HPerson();
		hperson = hPersonService.getOne(1L);

		log.debug("testGetOne(1L): {}" + hperson);
		//assertEquals(2,hperson.getColors().size());
	
	}
	
	@Test
	public void testGetAll() {
		Collection<HPerson> hpersons = hPersonService.getAll();
		
		log.debug(hpersons);
//		assertEquals(3,hpersons.size());
	}
		
	@Test
	public void testIterator(){
		Collection<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		
		Iterator<String> iterator = test.iterator();
		log.debug(iterator);
		while (iterator.hasNext()){
			log.debug(iterator.next());
		}
		
		
	}
}
