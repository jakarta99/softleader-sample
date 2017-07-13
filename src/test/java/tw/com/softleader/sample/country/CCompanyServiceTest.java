package tw.com.softleader.sample.country;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCompanyServiceTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private CCompanyDao cCompanyDao = new CCompanyDao();

	@Test
	public void testCrud() {
		int originalSize = cCompanyDao.findAll().size();
		log.debug("test findAll originalSize-->" + originalSize);

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

		cCompanyDao.findOne(DeltaID);

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

		log.debug("test Delete Delta2.getId-->" + Delta2.getId());
		cCompanyDao.delete(Delta2.getId());
		log.debug("test after delete Delta2.getId-->" + Delta2.getId());

		int finalSize = cCompanyDao.findAll().size();
		log.debug("test findAll finalSize-->" + cCompanyDao.findAll().size());
		
		assertEquals(finalSize, originalSize);

	}

}
