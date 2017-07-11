package tw.com.softleader.sample.sports;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SPersonServiceTest {

	private Logger log = Logger.getLogger(SPersonServiceTest.class);

	SportsService sport = new SportsService();
	SPersonService spersonSv = new SPersonService();

	@Test
	public void getAllTest() {
		log.info("getall: " + spersonSv.getAll());
	}

	@Test
	public void getOneTest() {
		log.info("GetOne: " + sport.getOne(1L));
	}

	@Test
	public void testInsertAndUpdateAndDelete() {

		SportDao sportD = new SportDao();

		SPerson insperson = new SPerson();

		Sport inssport = new Sport();

		Collection<Sport> sportarr = new ArrayList<Sport>();

		// insert

		insperson.setName("Kevin");
		insperson.setIdnum("4");
		inssport = sport.getOne(3L); // 設定sport
		sportarr.add(inssport); // 新增SPORT
		insperson.setSports(sportarr); // 新增sport給person

		spersonSv.insert(insperson); // 測試person新增

		log.info("insert person Id:" + insperson.getId());

		// insertid

		Long tempinsertid = insperson.getId(); // 把id存起來等一下用

		SPerson Spersonchk = spersonSv.getOne(tempinsertid); // 把id存起來等一下用

		log.info("insert person sportname getonechk:" + Spersonchk.getSports().iterator().next().getName());

		// update
		sportarr.clear();
		inssport = sport.getOne(1L); // 設定另一個運動給kevin update
		sportarr.add(inssport);
		insperson.setSports(sportarr);

		spersonSv.update(insperson); // update

		log.info("update person :" + insperson);

		// delete

		Spersonchk = spersonSv.getOne(tempinsertid); // 刪除

		assertNull(Spersonchk);

	}

}
