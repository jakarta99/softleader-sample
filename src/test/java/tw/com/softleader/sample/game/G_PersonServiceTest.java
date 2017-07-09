package tw.com.softleader.sample.game;

import static org.junit.Assert.*;

import java.util.Collection;
//import java.util.List;

import org.apache.log4j.Logger;

import org.junit.Test;

public class G_PersonServiceTest {
	private G_PersonService personService = new G_PersonService();
	private Logger log = Logger.getLogger(personService.getClass());

	@Test
	public void testGetOne() {
		log.info("2:" + personService.getOne((long) 1));
	}

	@Test
	public void testGetAll() {

		Collection<G_Person> persons = personService.getAll();

		for (G_Person person : persons) {
			log.info("2:" + person);
		}
	}

	@Test
	public void testInsertUpdateDelete() {
		G_Person Input = new G_Person();
		Input.setId((long) 3);
		Input.setpName("Duke");
		Input.setpIdno("987");
		personService.insert(Input);

		G_Person patch = new G_Person();

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
