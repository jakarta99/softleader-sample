package tw.com.softleader.sample.color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HPersonServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	HPersonService hPersonService = new HPersonService();
	
	ColorService colorService = new ColorService();
	
	ColorDao colorDao = new ColorDao();
	
	HPersonDao dao= new HPersonDao();

	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetOne() {
		HPerson hperson = new HPerson();
		hperson = hPersonService.getOne(3L);

		log.debug("testGetOne(3L): {}" + hperson);
		assertEquals(2,hperson.getColors().size());
		
		hperson = hPersonService.getByIdNo(hperson.getIdNo());
		log.debug("testGetByColor(3L): {}" + hperson);
		assertEquals(2,hperson.getColors().size());
	}
	
	@Test
	public void testGetAll() {
		Collection<HPerson> hpersons = hPersonService.getAll();
		
		log.debug(hpersons);
		assertEquals(3,hpersons.size());
	}
	
	
	@Test
	public void testCRUD() {
		
		HPerson insertHperson = new HPerson();
		
		Collection<Color> colors = new ArrayList<Color>();
		insertHperson.setName("Coco");
		insertHperson.setIdNo("3");
		
		Color colorGreen = new Color();
		colorGreen = colorService.getOne(3L);  // INSERT COLOR GREEN
		colors.add(colorGreen);
		
		Color colorRen = new Color();
		colorRen = colorService.getOne(1L);  // INSERT COLOR RED
		colors.add(colorRen);
		
		insertHperson.setColors(colors);  
		
		hPersonService.insert(insertHperson);
		
		Long generatedId = insertHperson.getId();
		log.debug("insert generatedId:" + generatedId);
		
		//檢查是否有新增
		HPerson hperson = hPersonService.getOne(generatedId);
	
		assertEquals("Coco", hperson.getName());
		assertEquals(2, hperson.getColors().size());
		
		/** update*/
		colors.clear();
		Color colorBlack = new Color();
		colorBlack = colorService.getOne(2L);  // INSERT COLOR BLACK
		colors.add(colorBlack);

		insertHperson.setColors(colors); //UPDATE COLOR FROM GREEN TO BLACK
		
		hPersonService.update(insertHperson);

		//檢查是否有修改

		
		/** delete*/
		hPersonService.delete(generatedId); //DELET JUST INSERT AND UPDATE
		
		//檢查是否有刪除


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
