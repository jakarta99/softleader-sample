package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MPersonServiceTest {

	private Logger log = LoggerFactory.getLogger(MPersonServiceTest.class);

	MPersonService mpersonService = new MPersonService();
	MovieService movieService = new MovieService();

	@Test
	public void testGetAll() {
		log.info("Get All" + mpersonService.getAll());
	}

	@Test
	public void testGetOne() {
		Long idm = 4L;
		log.info("Get One:" + mpersonService.getOne(idm));
	}

	@Test
	public void testInsertUpdateDelete() {
		Long idm = 3L;
		MPerson mperson = new MPerson();
		Movie movie = new Movie();
		Collection<Movie> movies = new ArrayList<Movie>();

		mperson.setName("Jack");
		mperson.setIdno("A123456788");
		movie.setId(3L);
		movie.setName("King Arthor legend of the sword");
		movie.setPrice("300");
		movies.add(movie);
		mperson.setMovie(movies);

		log.info("Insert Mperson:" + mpersonService.getAll().size());
		log.info("Insert Movie:" + movieService.getAll().size());
		Long generatedID = mperson.getId();

		movie.setId(idm);
		movie.setName("Despicable Me3");
		movie.setPrice("350");
		movies.add(movie);
		mperson.setMovie(movies);
		mpersonService.update(mperson);

		log.info("Update:" + mpersonService.getOne(idm));

		movieService.delete(generatedID);
		mpersonService.delete(mperson.getId());
		assertNull(mpersonService.getOne(generatedID));
	}

}