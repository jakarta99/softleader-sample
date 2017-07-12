package tw.com.softleader.sample.country;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.color.HPerson;

public class CCompanyServiceTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private CCompanyService cCompanyService = new CCompanyService();
	private CCompanyDao cCompanyDao = new CCompanyDao();

	// @Test
	// public void testGetOne(){
	// CCompany cCompany = new CCompany();
	// cCompany = cCompanyService.getOne(1L);
	//
	// log.debug("testGetOne(1L): {}" + cCompany);
	// assertEquals(3,cCompany.getCPersons().size());
	//
	// }

	@Test
	public void testCrud() {

		log.debug("test findAll ccompany.Size-->" + cCompanyDao.findAll().iterator().next().getId());

		CCompany Delta = new CCompany();
		Collection<CPerson> DeltaPeople = new ArrayList<CPerson>();
		CPerson Yumi = new CPerson();
		Collection<Country> insertCountry = new ArrayList<Country>();

		Country newCountry1 = new Country();
		newCountry1.setName("Easter Island");
		newCountry1.setSize("Tiny");
		insertCountry.add(newCountry1);

		Country newCountry2 = new Country();
		newCountry2.setName("Brazil");
		newCountry2.setSize("Large");
		insertCountry.add(newCountry2);

		Yumi.setName("Yumi");
		Yumi.setIdNo("A123456799");
		Yumi.setCountries(insertCountry);
		DeltaPeople.add(Yumi);

		Delta.setName("Delta");
		Delta.setCPersons(DeltaPeople);

		cCompanyDao.insert(Delta);

		Long DeltaID = Delta.getId();
		log.debug("test insert delta id-->" + DeltaID);

		CCompany DeltaOut = cCompanyDao.findOne(DeltaID);

		Collection<CPerson> DeltaPeople2 = new ArrayList<CPerson>();
		Collection<Country> updateCountryList = new ArrayList<Country>();
		Country updateCountry = new Country();
		updateCountry.setName("Portugal");
		updateCountry.setSize("Large");
		updateCountry.setP_ID(Delta.getCPersons().iterator().next().getCountries().iterator().next().getP_ID());
		updateCountryList.add(updateCountry);

		CPerson Yumi2 = new CPerson();
		Yumi2.setName("Yumi2");
		Yumi2.setIdNo("A123456779");
		Yumi2.setCountries(updateCountryList);
		DeltaPeople2.add(Yumi2);

		CCompany Delta2 = new CCompany();
		Delta2.setName("Delta2");
		Delta2.setId(DeltaID);
		Delta2.setCPersons(DeltaPeople2);

		cCompanyDao.update(Delta2);

		cCompanyDao.delete(Delta2.getId());

	}

}
