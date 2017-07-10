package tw.com.softleader.sample.color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HPersonServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	HPersonService hPersonService = new HPersonService();
	
	ColorService colorService = new ColorService();

	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetOne() {
		HPerson hperson = new HPerson();
		hperson = hPersonService.getOne(1L);

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
		
		ColorDao colorDao = new ColorDao();
		
		HPerson insertHperson = new HPerson();
		Color color = new Color();
		Collection<Color> colors = new ArrayList<Color>();
		insertHperson.setName("Angle");
		insertHperson.setIdNo("1");
		
		color = colorService.getOne(3L);  
		colors.add(color);
		insertHperson.setColors(colors);  // INSERT COLOR GREEN
		
		hPersonService.insert(insertHperson);
		
		Long generatedId = insertHperson.getId();
		log.debug("insert generatedId:" + generatedId);
		
		//檢查是否有新增
		HPerson hperson = hPersonService.getOne(generatedId);
		
		log.debug("get color's id: " + hperson.getColors().iterator().next().getId());
		
		Color insertColors = new Color();
		insertColors = colorDao.findOne(hperson.getColors().iterator().next().getId());
		
		assertEquals("Green", insertColors.getName());
		
		
		/** update*/
		colors.clear();
		color = colorService.getOne(2L);
		colors.add(color);
		insertHperson.setColors(colors); //UPDATE COLOR FROM GREEN TO BLACK
		
		hPersonService.update(insertHperson);
		
		//檢查是否有修改
		hperson = hPersonService.getOne(generatedId);
		insertColors = colorDao.findOne(hperson.getColors().iterator().next().getId());
		
		assertEquals("Black", insertColors.getName());
		
		/** delete*/
		hPersonService.delete(generatedId); //DELET JUST INSERT AND UPDATE
		
		//檢查是否有刪除
		hperson = hPersonService.getOne(generatedId);
		
		assertNull(hperson);

	}
}
