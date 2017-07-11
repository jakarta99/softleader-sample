package tw.com.softleader.sample.kperson;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.com.softleader.sample.KPerson.KPerson;
import tw.com.softleader.sample.KPerson.KPersonService;
import tw.com.softleader.sample.cloth.Cloth;

public class KPersonServiceTest {
	
	private Logger log = LoggerFactory.getLogger(KPersonServiceTest.class);
	
	private KPersonService kPersonService = new KPersonService();

	@Test
	public void testCrud() {
		
		KPerson one = kPersonService.getOne(1l);
		System.out.println("getOne : " + one);
		one.setName("Robert_Wang");
		if (one.getClothes() != null && !one.getClothes().isEmpty()) {
			Cloth cloth = one.getClothes().stream().findAny().get();
			cloth.setColor("GREEN");
		}
		kPersonService.update(one);
		
		Collection<KPerson> kPersons = kPersonService.getAll();
		System.out.println(kPersons);
		
//		
//		
//		kPersonService.delete(5L);
//		Collection<KPerson> kPersons = kPersonService.getAll();
//		System.out.println(kPersons);
//		KPerson newPerson = new KPerson();
//		newPerson.setName("RAY");
//		newPerson.setIdNo("80");
//		
//		Collection<Cloth> clothes = new ArrayList<>();
//		Cloth cloth = new Cloth();
//		cloth.setName("SHIRT");
//		cloth.setColor("WHITE");
//		clothes.add(cloth);
//		Cloth cloth2 = new Cloth();
//		cloth2.setName("SHIRT");
//		cloth2.setColor("BLACK");
//		clothes.add(cloth2);
//		newPerson.setClothes(clothes); 
//		kPersonService.insert(newPerson);
//		Long generatedId = newPerson.getId();
//		System.out.println("generatedId :" + generatedId);
//		 kPersons = kPersonService.getAll();
//		System.out.println(kPersons);
		
		
		
	}



	
}
