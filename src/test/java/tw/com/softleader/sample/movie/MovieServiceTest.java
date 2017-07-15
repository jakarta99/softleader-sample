package tw.com.softleader.sample.movie;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

//public class MovieServiceTest {
//	private Logger log = Logger.getLogger(this.getClass());
//	private MovieService movieService = new MovieService();
//
//	@Test
//	public void testGetAll() {
//		Collection<Movie> movies = movieService.getAll();
//
//		for (Movie movie : movies) {
//			log.info("Message:" + movie);
//		}
//
//	}
//
//	@Test
//	public void testGetOne() {
//		log.info("Message:" + movieService.getOne(2L));
//	}
//
//	@Test
//	public void testInsertUpdateDelete() {
//		Movie movie1 = new Movie();
//		movie1.setId(6L);
//		movie1.setName("Spider Man");
//		movie1.setPrice("600");
//
//		movieService.insert(movie1);
//
//		log.info("Message:" + movie1.getId());
//
//		Movie movie2 = new Movie();
//		movie2.setId(6L);
//		movie2.setName("The Hunt");
//		movie2.setPrice("700");
//		movieService.update(movie2);
//		log.info("Message:" + movie2.getId());
//
//		movieService.delete(6L);
//	}
//}