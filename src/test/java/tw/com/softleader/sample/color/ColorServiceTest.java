package tw.com.softleader.sample.color;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ColorServiceTest {
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
			System.out.println(color);
		}
	}
	
	@Test
	public void testGetOne() {
		
		Color color0 = new Color();
		color0 = colorService.getOne(0L);
		
		Color color2 = new Color();
		color2 = colorService.getOne(2L);
		
		System.out.println("testGetOne(0L): " + color0);
		System.out.println("testGetOne(2L): " + color2);

	}
	
	@Test
	public void testInsertAndTestUpdateAndTestDelete() {
		
		testGetAll();

		/** testInsert */
		System.out.println("--------------testInsert----------------");
		Color newColor = new Color();
		newColor.setId(4L);
		newColor.setName("white");
		newColor.setCode("#000000");

		colorService.insert(newColor);
		
		testGetAll();
		
		/** testUpdate */
		System.out.println("--------------testUpdate----------------");
		Color updateColor = new Color();
		updateColor.setId(4L);
		updateColor.setName("Blue");
		updateColor.setCode("#0000FF");
		colorService.update(updateColor);
		
		testGetAll();

		/** testDelete */
		System.out.println("--------------testDelete----------------");
		colorService.delete(4L);

		testGetAll();
	}
}
