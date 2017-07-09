package tw.com.softleader.sample.country;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CPersonServiceTest {

	private Logger log = Logger.getLogger(this.getClass());

	CPersonService cPersonService = new CPersonService();

//	@Test
//	public void testGetOnePerson() {
//		log.info("2:testGetOne-->" + cPersonService.getOne(1L));
//	}
//
//	@Test
//	public void testGetAllPerson() {
//		Collection<CPerson> cPersons = cPersonService.getAll();
//		for (CPerson cPerson : cPersons) {
//			log.info("2:testGetAll-->" + cPerson);
//		}
//	}

//	@Test
//	public void testInsertUpdateDeletePerson() {
//		CPerson insertNew = new CPerson();
//		insertNew.setName("Joe");
//		insertNew.setIdNo("A123456789");
//		cPersonService.insert(insertNew);
//
//		log.info("2:testInsertNew.getId-->" + insertNew.getId());
//
//		CPerson update = new CPerson();
//		update.setId(insertNew.getId());
//		update.setName("Japan");
//		update.setIdNo("Medium");
//		cPersonService.update(update);
//
//		cPersonService.delete(insertNew.getId());
//	}
	
	@Test
	public void testGetOneCountry() {
	 Long pID=1L;
		log.info("2:testGetOne-->" +cPersonService.getOne(pID).getCountryid()+ cPersonService.getOne(pID).getCountryName()+cPersonService.getOne(pID).getSize());
	}

	@Test
	public void testGetAllCountry() {
		Collection<CPerson> cPersons = cPersonService.getAll();
		for (CPerson cPerson : cPersons) {
			log.info("2:testGetAll-->" + cPerson);
		}
	}
	@Test
	public void testInsertUpdateDeleteCountry() {
		CPerson insertNew = new CPerson();
		insertNew.setCountryid(444L);
		insertNew.setCountryName("South Korea");
		insertNew.setSize("Medium");
		cPersonService.insert(insertNew);

		log.info("2:testInsertNew-->" + insertNew.getCountryid()+insertNew.getCountryName()+insertNew.getSize());

		CPerson update = new CPerson();
		update.setCountryid(insertNew.getCountryid());
		update.setCountryName("Japan");
		update.setSize("Medium");
		cPersonService.update(update);

		cPersonService.delete(update.getCountryid());
	}

}
