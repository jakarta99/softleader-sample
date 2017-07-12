package tw.com.softleader.sample.country;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CCompanyServiceTest {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private CCompanyDao cCompanyDao=new CCompanyDao();
	
	@Test
	public void testCrud() {
		Collection<CCompany> ccompany = cCompanyDao.findAll();
		int originalSize = ccompany.size();
		
		CCompany Delta = new CCompany();
		Delta.setName("Delta");
		
		Collection<CPerson> DeltaPeople = new ArrayList<CPerson>();
		CPerson Yumi = new CPerson();
		Yumi.setName("Yumi");
		Yumi.setIdNo("A123456799");
		DeltaPeople.add(Yumi);
		
		CPerson Morgana = new CPerson();
		Morgana.setName("Morgana");
		Morgana.setIdNo("A123456798");
		DeltaPeople.add(Morgana);
		
		
		Delta.setCPersons(DeltaPeople);
		
		cCompanyDao.insert(Delta);
		log.debug("{}", Delta);
		
		Long DetlaID = Delta.getId();
		
		
	}

}
