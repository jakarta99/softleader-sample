package tw.com.softleader.sample.human;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumanServiceTest {

	private Logger log = LoggerFactory.getLogger(HumanServiceTest.class);
	
	private HumanService humanService = new HumanServiceImpl();

	@Test
	public void testCrud() {
		Collection<Human> humans = humanService.getAll();
		int originalSize = humans.size();
		
		Human humanA = new Human();
		humanA.setName("A");
		humanA.setGender("BOY");
		
		humanService.insert(humanA);
		log.debug("{}", humanA);
		
		Long generatedId = humanA.getId(); 
		
		
		humanA.setGender("GIRL");
		humanService.update(humanA);
		
		
		Human entity = humanService.getOne(generatedId);
		
		assertEquals("A", entity.getName());
		
		assertEquals("GIRL", entity.getGender());
		
		
		humanService.delete(generatedId);
		assertNull(humanService.getOne(generatedId));
		
		humans = humanService.getAll();
		int finalSize = humans.size();
		
		assertEquals(originalSize, finalSize);
		
	}

}
