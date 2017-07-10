package tw.com.softleader.sample.movie;

import java.util.Collection;
import org.junit.Test;

public class MovieServiceTest {

	private MovieService movieService = new MovieService();

	@Test
	public void testGetAll() {
		Collection<Movie> movies = movieService.getAll();

		for (Movie movie : movies) {
			System.out.println(movie);
		}

	}

	@Test
	public void testGetOne() {
		Movie movie = movieService.getOne(2L);
		System.out.println(movie);
	}

	@Test
	public void testInsertUpdateDelete() {
		Movie movie1 = new Movie();
		movie1.setId(6L);
		movie1.setName("Spider Man");
		movie1.setPrice("600");

		movieService.insert(movie1);

		Movie movie2 = new Movie();
		movie2.setId(6L);
		movie2.setName("The Hunt");
		movie2.setPrice("700");

		movieService.update(movie2);

		movieService.delete(6L);

	}

}