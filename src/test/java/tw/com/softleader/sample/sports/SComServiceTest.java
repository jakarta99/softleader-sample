package tw.com.softleader.sample.sports;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SComServiceTest {
	private Logger log = Logger.getLogger(SComServiceTest.class);

	SComService scomSv = new SComService();

	Collection<Sport> sportlist = null; // new ArrayList<Sport>();
	Collection<SPerson> spersonlist = null; // new ArrayList<SPerson>();
	Collection<SCom> scomlist = null; // new ArrayList<SCom>();
	SCom scom = null;
	SPerson sperson = null;
	Sport sport = null;

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
		int originalSize = scomSv.getAll().size();
		log.debug("test findAll originalSize-->" + originalSize);

		sportlist = new ArrayList<Sport>();
		spersonlist = new ArrayList<SPerson>();
		scomlist = new ArrayList<SCom>();

		sport.setName("Tennis");
		sport.setPeople("2or4");
		sportlist.add(sport);

		sport.setName("Boxing");
		sport.setPeople("2");
		sportlist.add(sport);

		sperson.setIdnum("5");
		sperson.setName("Kevin");
		sperson.setSports(sportlist);
		spersonlist.add(sperson);

		scom.setName("soft-leader");
		scom.setSperson(spersonlist);
		// ↑↑↑ insert 的假資料 ↑↑↑

		scomSv.insert(scom);
		log.debug("test insert (find kevin's id)" + scom.getId());

		// ↑↑↑ insert test ↑↑↑

		sport.setName("Golf");
		sport.setPeople("1");
		sportlist.add(sport);

		sport.setName("Boxing");
		sport.setPeople("2");
		sportlist.add(sport);

		sperson.setIdnum("6");
		sperson.setName("Kevin");
		sperson.setSports(sportlist);
		spersonlist.add(sperson);

		scom.setName("soft-leader");
		scom.setSperson(spersonlist);
		// ↑↑↑ update 的假資料 ↑↑↑

		scomSv.update(scom);
		log.debug("test insert (find kevin's id)" + scom.getId());

		// ↑↑↑ update test ↑↑↑

		log.debug("test insert (find kevin's id)" + scom.getId());
		// ↑↑↑ delete info ↑↑↑
		scomSv.delete(scom.getId());
		// ↑↑↑ delete test ↑↑↑

		log.debug("test insert (find kevin's id)" + scom.getId());
		// ↑↑↑ afterdelete info ↑↑↑

		assertEquals(scomSv.getAll().size(), originalSize);
	}

}
