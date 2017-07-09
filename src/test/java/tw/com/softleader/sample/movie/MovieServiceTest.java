package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieServiceTest {

	private Logger log = LoggerFactory.getLogger(MovieServiceTest.class);

	private MovieService movieService = new MovieService();

	@Test
	public void testGetAll() {
		Collection<Movie> movies = movieService.getAll();
		for (Movie movie : movies) {
			log.info("test get all" + movie);
		}
	}

	@Test
	public void testGetOne() {
		Movie movie = movieService.getOne(2L);
		log.info("test get one" + movie);
	}

	@Test
	public void testInsertUpdateDelete() {		
		Movie movie1 = new Movie();
		
		Long generatedID = movie1.getId();
		
		movie1.setName("Spider Man:Home Coming");
		movie1.setPrice("600");
		movieService.insert(movie1);
		
		assertEquals("Spider Man:Home Coming",movie1.getName());
		assertEquals("600",movie1.getPrice());
		
		movie1.setName("The Hunt");
		movie1.setPrice("700");
		movieService.update(movie1);
		
		assertEquals("The Hunt",movie1.getName());
		assertEquals("700",movie1.getPrice());

		movieService.delete(movie1.getId());
		assertNull(movieService.getOne(generatedID));
	}

}