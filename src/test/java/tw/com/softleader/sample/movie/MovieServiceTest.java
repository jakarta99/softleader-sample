package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class MovieServiceTest {

	private MovieService movieService = new MovieService();

	@Test
	public void testGetAll() {
		Collection<Movie> movies = movieService.getAll();
		for(Movie movie:movies){
			System.out.println(movie);
		}
	}
}