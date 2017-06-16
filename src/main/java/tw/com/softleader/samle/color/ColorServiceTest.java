package tw.com.softleader.samle.color;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Hilda
 *
 */
public class ColorServiceTest {

	@Test
	public void testGetAll() {
		// fail("Not yet implemented");
		ColorService colorService = new ColorService();
		String[] colors = colorService.getAll();
		assertEquals("Red", colors[0]);
		assertEquals("Yellow", colors[1]);
		assertEquals("Blue", colors[2]);

	}

}
