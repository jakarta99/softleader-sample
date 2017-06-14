package tw.com.softleader.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CalculateServiceTest {

	@Test
	public void testSum() {
		CalculateService calculateService = new CalculateService();
		assertEquals(5, calculateService.sum(3, 2));

		assertEquals(8, calculateService.sum(3, 5));

		assertEquals(10, calculateService.sum(3, 7));

	}

	@Test
	public void testSubstract() {
		CalculateService calculateService = new CalculateService();
		assertEquals(8, calculateService.substract(15, 7));
	}

	@Test
	public void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		CalculateService calculateService = new CalculateService();
		assertEquals(1,calculateService.divide(3,3,0));
		
		
	}

	@Test
	public void testRemainder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSquare() {
		fail("Not yet implemented");
	}

	@Test
	public void testPower3() {
		CalculateService calculateService = new CalculateService();
		assertEquals(27, calculateService.power3(3));
	}

}
