package tw.com.softleader.sample.sports;

import static org.junit.Assert.assertEquals;

public class SportsServiceTest {

	public void bookServiceTest() {
		SportsService sports = new SportsService();
		String[] checkname = sports.getall();
		
		assertEquals("Baseball",  checkname[0]);
		assertEquals("Tennis",  checkname[1]);
		assertEquals("Swimming", checkname[2]);
		assertEquals("Basketball",  checkname[3]);

		
		}
//		for (String sport : sports.sports) {
//
//		}

	}


