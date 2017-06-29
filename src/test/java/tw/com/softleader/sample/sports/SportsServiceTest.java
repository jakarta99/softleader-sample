package tw.com.softleader.sample.sports;

import java.util.Collection;

import org.junit.Test;

import static java.lang.System.out;

public class SportsServiceTest {
	private SportsService test1 = new SportsService();
	Collection<Sport> sports = test1.getAll();

	@Test
	public void getAllTest() {
		for (Sport sport : sports) {
			out.println(sport);
		}
	}

	@Test
	public void getOneTest() {
		out.println(test1.getOne(1L));
	}

	@Test
	public void testInsertAndUpdateAndDelete() {

		Sport insertdata = new Sport();
		insertdata.setId(5L);
		insertdata.setName("Tennis");
		insertdata.setPeople("2");

		Sport updatedata = new Sport();
		updatedata.setId(5L);
		updatedata.setName("boxing");
		updatedata.setPeople("2");

		test1.insert(insertdata);

		test1.update(updatedata);

		test1.delete(5L);

	}

}