package tw.com.softleader.sample.sports;

import static org.junit.Assert.assertEquals;

public class SportsServiceTest {
	public void assertEquals(String sport1, String sport2, String sport3, String sport4, String[] sport) {
	}

	public void bookServiceTest() {
		SportsService sports = new SportsService();
		for (String sport : sports.getall()) {
			assertEquals("Baseball", "Tennis", "Swimming", "Basketball", sports.getall());

		}

	}

}
