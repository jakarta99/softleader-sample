package tw.com.softleader.sample.color;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Hilda
 *
 */
public class ColorServiceTest {
	ColorService colorService = new ColorService();
	
	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAll() {
		
		assertEquals(3, colorService.getAll().size());
	}
	
	@Test
	public void testGetOne() {
		
		assertEquals(1, colorService.getOne(0).getId());
		assertEquals("Red", colorService.getOne(0).getName());
		assertEquals("#FF0000", colorService.getOne(0).getCode());

		assertEquals(2, colorService.getOne(1).getId());
		assertEquals("Black", colorService.getOne(1).getName());
		assertEquals("#FFFFFF", colorService.getOne(1).getCode());
		
		assertEquals(3, colorService.getOne(2).getId());
		assertEquals("Green", colorService.getOne(2).getName());
		assertEquals("#00FF00", colorService.getOne(2).getCode());

	}
	
	@Test
	public void testInsertAndTestUpdateAndTestDelete() {

		/** testInsert */
		Color newColor = new Color();
		newColor.setId(4);
		newColor.setName("white");
		newColor.setCode("#000000");

		colorService.insert(newColor);

		/** testGetOne */
		assertEquals("white", colorService.getOne(3).getName());
		System.out.print("test Get Insert's Name : " + colorService.getOne(3).getName() + " ,Color Code: " + colorService.getOne(3).getCode() + "\n-------------------------------\n");

		/** testUpdate */
		Color updateColor = new Color();
		updateColor.setId(3);
		updateColor.setName("Blue");
		updateColor.setCode("#0000FF");
		colorService.update(updateColor);

		assertEquals("Blue", colorService.getOne(2).getName());
		System.out.print("test Get Update's Name : " + colorService.getOne(2).getName() + " ,Color Code: " + colorService.getOne(2).getCode() + "\n-------------------------------\n");

		/** testDelete */
		colorService.delete(2);

		assertEquals(3, colorService.getAll().size());
		System.out.print(
				"test Get Delete's Color Black, So Id 2 will change --> Name : " + colorService.getOne(1).getName() + " ,Color Code: " + colorService.getOne(1).getCode() + "\n-------------------------------\n");
	}

}
