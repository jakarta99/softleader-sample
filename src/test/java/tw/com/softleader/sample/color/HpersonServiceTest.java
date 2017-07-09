package tw.com.softleader.sample.color;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HpersonServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	HpersonService hPersonService = new HpersonService();
	
	ColorService colorService = new ColorService();

	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetOne() {
		Hperson hperson = new Hperson();
		hperson = hPersonService.getOne(1L);

		log.debug("testGetOne(1L): {}" + hperson);
	}
	
	@Test
	public void testGetAll() {
		Collection<Hperson> hpersons = hPersonService.getAll();
		
		log.debug(hpersons);
		log.debug("hperson.size():" + hpersons.size());
	}
	
	@Test
	public void testInsertAndUpdate() {
		
		Hperson insertHperson = new Hperson();
		Color color = new Color();
		Collection<Color> colors = new ArrayList<Color>();
		insertHperson.setName("Angle");
		insertHperson.setIdNo("1");
		
		color = colorService.getOne(3L);  // INSERT COLOR GREEN
		colors.add(color);
		insertHperson.setColors(colors);
		
		hPersonService.insert(insertHperson);
		
		Long generatedId = insertHperson.getId();
		log.debug("insert generatedId:" + generatedId);
		
		/** update*/
		
		
		/** delete*/
		hPersonService.delete(generatedId); //DELET JUST INSERT AND UPDATE

	}
}
