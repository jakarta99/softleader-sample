package tw.com.softleader.sample.country;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CPersonServiceTest {

	private Logger log = Logger.getLogger(this.getClass());

	CPersonService cPersonService = new CPersonService();
	CountryService countryService = new CountryService();

	Long pID = 1L;

	@Test
	public void testGetOne() {

		log.info("2:testGetOne-->" + cPersonService.getOne(pID));
	}

	@Test
	public void testGetAll() {
		
		log.info("2:testGetAll -->" + cPersonService.getAll());
	}

	@Test
	public void testCRUD() {
		Country country = new Country();
		CPerson insertNew = new CPerson();
		Collection<Country> countries = new ArrayList<Country>();
		Long cID=3L;

		insertNew.setName("Joe");
		insertNew.setIdNo("A123456789");

		country.setId(cID);
		country.setName("Singapore");
		country.setSize("Tiny");
		
		countries.add(country);
		insertNew.setCountries(countries);

		cPersonService.insert(insertNew);
		
		log.info("2:CRUD insert--> New Person Total: "+cPersonService.getAll().size());
		log.info("2:CRUD insert--> New Country Total: "+countryService.getAll().size());
		
		Long generatedId = insertNew.getId();

		log.info("2: CRUD generatedId--> No. "+generatedId);
		
		country.setId(cID);//Choose existing ID
		country.setName("Macau");//Set new Name
		country.setSize("Tiny");//Set New Size
		
		countries.add(country);
		insertNew.setCountries(countries);
		cPersonService.update(insertNew);
		
		log.info("2:CRUD update--> "+countryService.getOne(cID));
		
		
		cPersonService.delete(generatedId);//It only deletes the data on CPerson
		countryService.delete(cID);
		
		log.info("2:CRUD delete-->New Person Total: "+cPersonService.getAll().size());
		log.info("2:CRUD delete-->New Country Total: "+countryService.getAll().size());
		
	}

}
