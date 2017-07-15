package tw.com.softleader.sample.sports;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

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
		log.info("Jack : " + scomSv.getOne(1L).getSperson().iterator().next());
		log.info("Jack's sport : " + scomSv.getOne(1L).getSperson().iterator().next().getSports());

	}

	@Test
	public void testCrud() {
		int originalSize = scomSv.getAll().size();
		log.debug("test findAll originalSize-->" + originalSize);

		Collection<SPerson> insertSpersonlist = new ArrayList<SPerson>();
		Collection<Sport> insertSportlist = new ArrayList<Sport>();

		SCom insertScom = new SCom();
		SPerson insertSperson = new SPerson();
		Sport insertSport1 = new Sport();
		Sport insertSport2 = new Sport();

		insertSport1.setName("Tennis");
		insertSport1.setPeople("2or4");
		insertSportlist.add(insertSport1);

		insertSport2.setName("Boxing");
		insertSport2.setPeople("2");
		insertSportlist.add(insertSport2);

		insertSperson.setIdnum("5");
		insertSperson.setName("Kevin");
		insertSperson.setSports(insertSportlist);
		insertSpersonlist.add(insertSperson);

		insertScom.setName("soft-leader");
		insertScom.setSperson(insertSpersonlist);
		// ↑↑↑ insert 的假資料 ↑↑↑

		scomSv.insert(insertScom);
		Long tempInsertId = insertScom.getId();
		log.debug("test insert (find insertScom id)" + insertScom.getId());
		log.debug("test insert (find kevin)" + scomSv.getOne(tempInsertId).getSperson().iterator().next().getName());
		log.debug("test insert (find kevin's sport)"
				+ scomSv.getOne(tempInsertId).getSperson().iterator().next().getSports().iterator().next().getName());
		log.debug("test insert (find kevin's sport)"
				+ scomSv.getOne(tempInsertId).getSperson().iterator().next().getSports().iterator());

		// ↑↑↑ insert test ↑↑↑

		Collection<SPerson> updateSpersonlist = new ArrayList<SPerson>();
		Collection<Sport> updateSportlist = new ArrayList<Sport>();

		SCom updateScom = new SCom();
		SPerson updateSperson = new SPerson();
		Sport updateSport1 = new Sport();
		Sport updateSport2 = new Sport();

		updateSport1.setName("Golf");
		updateSport1.setPeople("1");
		updateSportlist.add(updateSport1);

		updateSport2.setName("Boxing");
		updateSport2.setPeople("2");
		updateSportlist.add(updateSport2);

		updateSperson.setIdnum("6");
		updateSperson.setName("Kevin");
		updateSperson.setSports(updateSportlist);
		updateSpersonlist.add(updateSperson);

		updateScom.setName("soft-leader");
		updateScom.setId(tempInsertId);
		updateScom.setSperson(updateSpersonlist);
		// ↑↑↑ update 的假資料 ↑↑↑

		scomSv.update(updateScom);
		log.debug("test update (find Scom id)" + updateScom.getId());
		Long tempUpdateId = updateScom.getId();
		log.debug("test update (find kevin's id)" + updateScom.getId());
		log.debug("test update (find kevin)" + scomSv.getOne(tempUpdateId).getSperson().iterator().next().getName());
		log.debug("test update (find kevin's sport)"
				+ scomSv.getOne(tempUpdateId).getSperson().iterator().next().getSports().iterator().next().getName());
		log.debug("test update (find kevin's sport)"
				+ scomSv.getOne(tempUpdateId).getSperson().iterator().next().getSports().iterator());
		// ↑↑↑ update test ↑↑↑

		log.debug("delete start");
		// ↑↑↑ delete info ↑↑↑
		scomSv.delete(tempUpdateId);
		// ↑↑↑ delete test ↑↑↑

		log.debug("test delete (find kevin's id)" + updateScom.getId());
		// ↑↑↑ afterdelete info ↑↑↑

		assertEquals(scomSv.getAll().size(), originalSize);
	}

}
