package tw.com.softleader.sample.movie;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import org.junit.Test;

import tw.com.softleader.sample.movie.MovieService;

public class MovieServiceTest {

	@Test
	public void testString() {
		MovieService movieService = new MovieService();
		String[] movies = movieService.getAll();
		assertEquals("Captain of America", movies[0]);
		assertEquals("Silence of the lambs", movies[1]);
		assertEquals("King Arthor legend of the sword", movies[2]);
		assertEquals("Crimson Peak", movies[3]);
		assertEquals("The Godfather", movies[4]);

	}
}
