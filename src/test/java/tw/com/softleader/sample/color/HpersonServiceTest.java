package tw.com.softleader.sample.color;

import static org.junit.Assert.fail;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class HpersonServiceTest {
	private Logger log = Logger.getLogger(this.getClass());
	
	HpersonService hPersonService = new HpersonService();

	@Deprecated
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetOne() {
		Hperson hperson = new Hperson();
		hperson = hPersonService.getOne(1L);

		log.debug("testGetOne(1L): {}" + hperson);
	}
	
	@Test
	public void testGetAll() {
		Collection<Hperson> hpersons = hPersonService.getAll();

		log.debug(hpersons);
		log.debug("hperson.size():" + hpersons.size());
	}

}
