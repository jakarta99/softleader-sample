package tw.com.softleader.sample.color;

import static org.junit.Assert.*;

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
		
//		String[] colors = colorService.getAll();
//		assertEquals("Red", colors[0]);
//		assertEquals("Yellow", colors[1]);
//		assertEquals("Blue", colors[2]);
	}
	
	@Test
	public void testInsertAndTestUpdateAndTestDelete() {
		Color newColor = new Color();
		newColor.setId(3);
		newColor.setName("white");
		newColor.setCode("FFFFFF");
		
		colorService.insert(newColor);
//		for(String color:colorService.getAll()) {
//			System.out.println(drink);
//		}
		assertEquals("white", colorService.getOne(2).getName());
		System.out.println("-----------------------");
//		
//		colorService.update("water", 3);
//		for(String drink:colorService.getAll()) {
//			System.out.println(drink);
//		}
//		assertEquals("water", colorService.getOne(3));
//		System.out.println("-----------------------");
//		colorService.delete("water");
//		
//		for(String drink:colorService.getAll()) {
//			System.out.println(drink);
//		}
//		
//		assertEquals(3, colorService.getAll().length);
	}

}
