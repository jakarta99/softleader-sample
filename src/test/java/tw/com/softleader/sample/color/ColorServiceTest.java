package tw.com.softleader.sample.color;

import static org.junit.Assert.fail;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ColorServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	ColorService colorService = new ColorService();
	
	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAll() {
		
		Collection<Color> colors = colorService.getAll();
		
		for(Color color:colors) {
			//System.out.println(color);
			log.debug(color);
		}
	}
	
	@Test
	public void testGetOne() {
		
		Color color0 = new Color();
		color0 = colorService.getOne(0L);
		
		Color color2 = new Color();
		color2 = colorService.getOne(2L);
		
		log.debug("testGetOne(0L): {}" + color0);
		log.debug("testGetOne(2L): {}" + color2);
	}
	
	@Test
	public void testInsertAndTestUpdateAndTestDelete() {
		
		testGetAll();

		/** testInsert */
		System.out.println("--------------testInsert----------------");
		Color newColor = new Color();
		//newColor.setId(4L);
		newColor.setName("white");
		newColor.setCode("#000000");

		colorService.insert(newColor);
		
		Long generatedId = newColor.getId();
		
		testGetAll();
		
		/** testUpdate */
		System.out.println("--------------testUpdate----------------");
//		Color updateColor = new Color();
//		updateColor.setId(4L);
		newColor.setName("Blue");
		newColor.setCode("#0000FF");
		colorService.update(newColor);
		
		testGetAll();

		/** testDelete */
		System.out.println("--------------testDelete----------------");
		//colorService.delete(4L);
		colorService.delete(generatedId);

		testGetAll();
	}
}
