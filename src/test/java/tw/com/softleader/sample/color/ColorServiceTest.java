package tw.com.softleader.sample.color;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Hilda
 *
 */
public class ColorServiceTest {
	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAll() {
		ColorService colorService = new ColorService();
		String[] colors = colorService.getAll();
		assertEquals("Red", colors[0]);
		assertEquals("Yellow", colors[1]);
		assertEquals("Blue", colors[2]);
	}

}
