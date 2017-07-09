package tw.com.softleader.sample.books;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

public class PersonServiceTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	private PersonService personService = new PersonService();
	@Test
	public void testGetAll() {
		Collection<Person> personS = personService.getAll();

		for (Person person : personS) {
			log.info("testGetAll:" + person);
		}

	}

	@Test
	public void testGetOne() {
		log.info("testGetOne:" + personService.getOne(2L));
	}

	@Test
	public void testInsertUpdateDelete() {
		Person person = new Person();
		person.setBookname("The Lord of the Rings");
		person.setBooktype("fantasy");
		person.setP_id(1L);
		personService.insert(person);
		Long generatedId=person.getId();
		log.info("testInsert" + person);
		
		
		person.setBookname("Java 8 API");
		person.setBooktype("educational");
		personService.update(person);
		log.info("testUpdate" + person);
		
		Person entity = personService.getOne(generatedId);
		assertEquals("Java 8 API",(entity.getBookname()));
		assertEquals("educational",(entity.getBooktype()));

		personService.delete(generatedId);

	}

}
