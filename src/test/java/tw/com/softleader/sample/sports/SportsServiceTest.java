package tw.com.softleader.sample.sports;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SportsServiceTest {

	@Test
	public void sportServiceTest() {

		SportsService sports = new SportsService();
		String[] checkname = sports.getall();

		assertEquals("Baseball", checkname[0]);
		assertEquals("Tennis", checkname[1]);
		assertEquals("Swimming", checkname[2]);
		assertEquals("Basketball", checkname[3]);

	}
	// for (String sport : sports.sports) {
	//
	// }

}
