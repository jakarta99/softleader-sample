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
		hperson = hPersonService.getOne(2L);

		log.debug("testGetOne(1L): {}" + hperson);
	}
	
	@Test
	public void testGetAll() {
		Collection<HPerson> hpersons = hPersonService.getAll();
		
		log.debug(hpersons);
		log.debug("hperson.size():" + hpersons.size());
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
		
		//TODO　檢查是否有新增
//		HPerson hperson = hPersonService.getOne(generatedId);
//		Iterator<Color> colorInsert = hperson.getColors().iterator();
//		Color checkInsert1 = colorService.getOne(colorInsert.next().getId());
//		Color checkInsert2 = colorService.getOne(colorInsert.next().getId());
//		
//		assertEquals("Coco", hperson.getName());
//		assertEquals("Green", checkInsert1.getName());
//		assertEquals("Red", checkInsert2.getName());
		
		
		/** update*/
		colors.clear();
		Color colorBlack = new Color();
		colorBlack = colorService.getOne(2L);  // INSERT COLOR BLACK
		colors.add(colorBlack);

		insertHperson.setColors(colors); //UPDATE COLOR FROM GREEN TO BLACK
		
		hPersonService.update(insertHperson);

		//檢查是否有修改
//		hperson = hPersonService.getOne(generatedId);
//		insertColors = colorDao.findOne(hperson.getColors().iterator().next().getId());
//		
//		assertEquals("Black", insertColors.getName());
		
		/** delete*/
		hPersonService.delete(generatedId); //DELET JUST INSERT AND UPDATE
//		
//		//檢查是否有刪除
//		hperson = hPersonService.getOne(generatedId);
//		
//		assertNull(hperson);

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
