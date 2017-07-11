package tw.com.softleader.sample.game;

import static org.junit.Assert.*;

import java.util.Collection;
//import java.util.List;

import org.apache.log4j.Logger;

import org.junit.Test;

public class GPersonServiceTest {
	private GPersonService personService = new GPersonService();
	private Logger log = Logger.getLogger(personService.getClass());

	@Test
	public void testGetOne() {
		log.info("2:" + personService.getOne((long) 1));
	}

	@Test
	public void testGetAll() {

		Collection<GPerson> persons = personService.getAll();

		for (GPerson person : persons) {
			log.info("2:" + person);
		}
	}

	@Test
	public void testInsertUpdateDelete() {
		GPerson Input = new GPerson();
		Input.setId((long) 3);
		Input.setpName("Duke");
		Input.setpIdno("987");
		personService.insert(Input);

		GPerson patch = new GPerson();

		patch.setId((long) 4);
		patch.setpName("Edward");
		patch.setpIdno("963");
		personService.update(patch);

		personService.delete((long) 4);

	}

	@Test 
	public void testGetGameByPerson(){
		
		assertNotNull( personService.getGameByPerson((long) 1));
		log.info("2:"+personService.getGameByPerson((long) 1));
	}

}
