package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MPersonServiceTest {

	private Logger log = LoggerFactory.getLogger(MPersonServiceTest.class);

	private MPersonService mpersonService = new MPersonService();

	@Test
	public void testGetAll() {
		Collection<MPerson> mpersons = mpersonService.getAll();
		for (MPerson mperson : mpersons) {
			log.info("test get all" + mperson);
		}
	}

	@Test
	public void testGetOne() {
		MPerson mperson = mpersonService.getOne(2L);
		log.info("test get one" + mperson);
	}

	@Test
	public void testInsertUpdateDelete() {		
		MPerson mperson = new MPerson();
		
		Long generatedID = mperson.getId();
		
		mperson.setName("Jack");
		mperson.setIdno("A123456788");
		mperson.setMoviename1("");
		mperson.setMoviename2("");
		mperson.setMoviename3("");
		mpersonService.insert(mperson);
		
//		assertEquals();
//		assertEquals();
//		assertEquals();
//		assertEquals();
//		assertEquals();
		
		mperson.setName("Mary");
		mperson.setIdno("A223456789");
		mperson.setMoviename1("");
		mperson.setMoviename2("");
		mperson.setMoviename3("");
		mpersonService.update(mperson);
		
//		assertEquals();
//		assertEquals();
//		assertEquals();
//		assertEquals();
//		assertEquals();

		mpersonService.delete(mperson.getId());
		assertNull(mpersonService.getOne(generatedID));
	}

}