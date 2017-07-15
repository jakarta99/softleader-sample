//package tw.com.softleader.sample.movie;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class MPersonServiceTest {
//
//	private Logger log = LoggerFactory.getLogger(MPersonServiceTest.class);
//
//	MPersonService mpersonService = new MPersonService();
//	MovieService movieService = new MovieService();
//
//	@Test
//	public void testGetAll() {
//		log.debug("Get All" + mpersonService.getAll());
//	}
//
//	@Test
//	public void testGetOne() {
//		Long idm = 1L;
//		log.debug("Get One:" + mpersonService.getOne(idm));
//	}
//
//	@Test
//	public void testInsertUpdateDelete() {
//		Long idm = 3L;
//		MPerson mperson = new MPerson();
//		Movie movie = new Movie();
//		Collection<Movie> movies = new ArrayList<Movie>();
//
//		mperson.setName("Jack");
//		mperson.setIdno("A123456788");
//		movie.setId(3L);
//		movie.setName("King Arthor legend of the sword");
//		movie.setPrice("300");
//		movies.add(movie);
//		mperson.setMovies(movies);
//
//		log.debug("Insert Mperson:" + mpersonService.getAll().size());
//		log.debug("Insert Movie:" + movieService.getAll().size());
//		Long generatedId = mperson.getId();
//
//		movie.setId(idm);
//		movie.setName("Despicable Me3");
//		movie.setPrice("350");
//		movies.add(movie);
//		mperson.setMovies(movies);
//		mpersonService.update(mperson);
//
//		log.debug("Update:" + mpersonService.getOne(idm));
//
//		mpersonService.delete(generatedId);
//		movieService.delete(idm);
//	}
//
//}