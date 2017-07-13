package tw.com.softleader.sample.sports;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SComServiceTest {
	private Logger log = Logger.getLogger(SComServiceTest.class);

	SComService scomSv = new SComService();

	@Test
	public void getAllTest() {
		log.info("getall: " + scomSv.getAll());
	}

	@Test
	public void getOneTest() {
		log.info("GetOne: " + scomSv.getOne(1L));
	}

	@Test
	public void testCrud() {
		

	}

}
